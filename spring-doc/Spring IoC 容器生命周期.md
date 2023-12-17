# Spring ApplicationContext Lifecycle

## 前言

> 关于Spring源码介绍的版本是：5.2.2.REALEASE，如果有流程不一致的地方，可以看下版本是否一致。

## 一、本文概览

> Spring IoC容器生命周期整体看下来比较简单，但细节还是比较多的，其中最核心的方法就是AbstractApplicationContext#refresh了，除此方法外，还有AbstractApplicationContext#start、stop、close方法。下面是整个IoC容器生命周期的概览图，接下来我们依次去分析其中的细节项。

![](https://img.markuszhang.com/img/20231217210027.png)


## 二、refresh-刷新阶段

> 刷新阶段是IoC生命周期中最为重要的一个阶段，里面包括上下文准备、容器创建、各种生命周期回调以及内建Bean创建等的操作，我们来看下其中的细节。

### 1、prepareRefresh()

容器刷新准备阶段，主要完成以下操作

- 设置容器启动时间-startupDate
- 容器关闭状态调整为false-closed(false)
- 容器活跃状态调整为true-active(true)
- 初始化PropertySources-initPropertySources()
    - 初始化上下文环境中的任何占位符属性源。
    - 默认空实现，由子类实现
- 检查Environment中必须存在的属性
- 初始化事件监听器集合
- 初始化早期Spring事件集合

### 2、obtainFreshBeanFactory()

创建BeanFactory阶段，该方法细节内容由AbstractRefreshableApplicationContext#refreshBeanFactory实现

- AbstractRefreshableApplicationContext#refreshBeanFactory

    - 如果当前上下文下有BeanFactory的话，就进行销毁Bean、关闭BeanFactory操作

    - 创建Spring框架内部唯一BeanFactory实现：DefaultListableBeanFactory

    - 设置BeanFactory ID，此ID用于反序列化操作
        - 自定义此BeanFactory，包括是否允许BeanDefinition覆盖操作、是否允许循环引用
            - Spring默认允许BeanDefinition覆盖操作，SpringBoot会自定义该实现，不允许BeanDefinition覆盖操作
            - Spring默认允许循环引用

    - 加载此BeanFactory下的所有BeanDefinition

    - 将此BeanFactory设置到当前Spring应用上下文中

- getBeanFactory()

    - 返回Spring应用上下文底层BeanFactory

### 3、prepareBeanFactory(ConfiguableListableBeanFactory)

将BeanFactory应用于上下文前的BeanFactory准备阶段。

- 关联当前上下文的ClassLoader
- 设置Bean表达式处理器（SPEL）-StandardBeanExpressionResolver
- 添加PropertyEditorRegistrar实现-ResourceEditorRegistrar
- 添加Aware回调接口BeanPostProcessor实现-ApplicationContextAwareProcessor
    - 六大Aware接口回调，包括：EnvironmentAware、EmbeddedValueResolverAware、ResourceLoaderAware、ApplicationEventPublisherAware、MessageSourceAware、ApplicationContextAware
- 忽略Aware回调接口作为依赖注入接口
- 注册ResolvableDependency对象-BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext
- 添加ApplicationListenerDetector，该类的作用是为了检测内建实现ApplicationListener接口的Bean
- 添加LoadTimeWeaverAwareProcessor，该类和AOP有关
- 设置临时类加载器-ContextTypeMatchClassLoader
- 注册单例Bean Environment、Map<String,Object> systemProperties以及OS环境变量

### 4、postProcessorBeanFactory(ConfigurableListableBeanFactory)

该方法为空实现，留给子类去重写。我们看到其子类实现都是WebApplicationContext，细节就不需要关注了，此方法的作用就是允许子类ApplicationContext实现中可以注册特殊的BeanPostProcessor
![](https://img.markuszhang.com/img/20231217210059.png)
### 5、invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory)

BeanFactory后置处理阶段，这里面开始调用BeanFactoryPostProcessor或者BeanDefinitionRegistry的后置处理方法，处理顺序如下所示：

- if beanFactory instanceof BeanDefinitionRegistry

    - 处理BeanDefinitionRegistry#postProcessBeanDefinitionRegistry(beanDefinitionRegistry)
        - 处理当前上下文的BeanFactoryPostProcessor中为BeanDefinitionRegistryPostProcessor的实例，调用其后置处理方法
        - 通过依赖查找注册在BeanFactory中并且实现了PriorityOrdered接口的BeanDefinitionRegistryPostProcessor实例，随后将其进行排序后进行后置处理方法的调用
        - 通过依赖查找注册在BeanFactory中并且实现了Ordered接口的BeanDefinitionRegistryPostProcessor实例，随后将其进行排序并进行后置方法的调用
        - 通过依赖查找注册在BeanFactory中的普通BeanDefinitionRegistryPostProcessor实例，然后进行后置方法的调用

    - 处理BeanFactoryPostProcessor#postProcessBeanFactory
        - 先处理BeanDefinitionRegistryPostProcessor
            - 后处理普通的BeanFactoryPostProcessor

- 处理剩余的BeanFactory中BeanFactoryPostProcessor
    - 也是按照优先级顺序来进行调用，首先要求没有在上面处理过，也就是在上面的if分支中处理过的。
    - 优先级按照 PriorityOrdered > Ordered > 普通

注册BeanPostProcessor以及临时类加载器，与AOP有关

### 6、registerBeanPostProcessor(ConfigurableListableBeanFactory)

- 注册BeanPostProcessorChecker，此类的作用是当Bean创建是不符合所有的BeanPostProcessor条件时记录一条消息
- 注册实现了PriorityOrdered接口的BeanPostProcessor，PriorityOrdered内部排序
- 注册实现了Ordered接口的BeanPostProcessor，Ordered内部排序
- 注册普通的BeanPostProcessor，无需排序
- 注册Spring内建的BeanPostProcessor（MergedBeanDefinitionPostProcessor），按照优先级进行排序
- 重新注册ApplicationListenerDetector（旧的删除，将其放至处理链的末尾）

### 7、initMessageSource()

初始化MessageSource，用于国际化

- 如果BeanFactory中有MessageSource，则通过依赖查找获取MessageSource实例，如果该实例支持层次性并且当前上下文的MessageSource没有设置父类实例，就设置一下它的父类MessageSource实例
- 如果BeanFactory中没有MessageSource实例，则初始化一个DelegatingMessageSource实例，并设置到当前上下文里，并且将当前实例注册到BeanFactory单例缓存中
  需要记住的是：每一个ApplicationContext都唯一对应一个MessageSource实例，但是实例内部是空内容。

### 8、initApplicationEventMulticaster()

初始化事件广播器，用于事件发布

- 如果BeanFactory中有ApplicationEventMulticaster实例，则通过依赖查找获取ApplicationEventMulticaster实例，并且设置到当前上下文中
- 如果BeanFactory中没有ApplicationEventMulticaster实例，则初始化一个SimpleApplicationEventMulticaster实例，用于事件广播，并且将该实例设置到当前上下文中以及注册到BeanFactory单例缓存中

### 9、onRefresh()

- 此方法留给子类去扩展，用于特定上下文的特殊的bean初始化工作
  此方法都是有web相关的ApplicationContext来扩展的，包括以下部分：
    - AbstractRefreshableWebApplicationContext
    - EmbeddedWebApplicationContext
    - GenricWebApplicationContext
    - StaticWebApplicationContext

至于它们都初始化了什么特殊的bean先不关心，知道有这部分功能即可

### 10、registerListeners()

注册监听器

- 首先注册上下文静态指定的监听器
- 注册底层BeanFactory容器中注册的ApplicationListener
- 发布早期事件（初始化事件广播之前发生的事件）

### 11、finishBeanFactoryInitialization(ConfigurableListableBeanFactory)

- 初始化ConversionService类，用于类型转换，如果BeanFactory中有此对象，则通过依赖查找获取ConversionService对象并设置到beanFactory对应字段中，如果没有，则不操作。
- 如果BeanFactory中没有EmbeddedValueResolvable，添加一个
- 初始化LoadTimeWeaverAware Bean对象，首先去容器中查找LoadTimeWeaverAware相关类的名称，并依次通过依赖查找(通过bean名称)，目的也是提前初始化
- 将临时类加载器设置为null，停止使用临时类加载器（这个类加载器可能跟Spring AOP有关，提前去加载一些必要的类）
- 冻结BeanDefinition注册行为，将DefaultListableBeanFactory中的beanDefinitionNames内容以数组的形式保存到frozenBeanDefinitionName字段中
- 提前初始化容器中所有的剩下的单例Bean

### 12、finishRefresh()

上下文完成刷新阶段

- 清除ResourceLoader缓存-clearResourceCaches
- 初始化Lifecycle对象-initLifeCycleProcessor
- 调用LifecycleProcessor#onRefresh方法
- 发布容器已刷新事件
- 向MBeanServer托管Live Bean

## 二、start-启动阶段

- 启动LifecycleProcessor
    - 依赖查找Lifecycle Beans
    - 启动Lifecycle Bean
- 发布容器已启动事件

## 三、close-关闭阶段

关闭此应用上下文，销毁BeanFactory中所有的Bean，销毁动作委托给doClose()方法

- closed(true)
- Live Beans JMX撤销托管
- 发布容器关闭事件
- 关闭LifecycleProcessor
    - 依赖查找所有的Lifecycle Beans
    - 停止 Lifecycle Beans
- 销毁Spring Beans
- 关闭BeanFactory
- 回调onClose()-子类实现
- 清除早期的事件监听器
- active(false)
- 删除Shutdown Hook，如果我们注册过的话

## 四、stop-停止阶段

- 停止LifecycleProcessor
    - 依赖查找Lifecycle Beans
    - 停止Lifecycle Bean
- 发布容器已停止事件

## 五、总结

对于Spring IoC容器的生命周期大致就是上述总结的流程，它包括刷新、启动、停止、关闭阶段，其中最为重要的就是刷新阶段了，其他是需要我们去手动调用才能生效的方法。对于这些阶段其中的某些细节，我目前也不是很清楚，接下来便继续开展其细节的学习记录，例如Bean生命周期、国际化、资源管理、类型转换等内容。

