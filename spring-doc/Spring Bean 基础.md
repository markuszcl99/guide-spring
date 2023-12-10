**写在最前面:**

本文运行的示例在我github项目中的spring-bean模块，源码位置: [spring-bean](https://github.com/markuszcl99/guide-spring/tree/main/spring-bean)

## 前言

> 为什么要先掌握 Spring Bean 的基础知识？

我们知道 Spring 框架提供的一个最重要也是最核心的能力就是管理 Bean 实例。以下是其原因：

- **核心组件：** Spring框架的核心是IoC（Inversion of Control）容器，而Bean是IoC容器的基本构建块。理解Spring Bean的概念和使用方式是学习和使用Spring框架的第一步。
- **依赖注入：** Spring框架通过依赖注入实现了对象之间的解耦。Bean是通过IoC容器进行管理和装配的，掌握Bean的基础知识帮助你理解依赖注入是如何工作的，从而更好地设计和组织应用程序的代码结构。
- **配置和管理：** Spring Bean的配置方式多种多样，包括XML配置、Java配置、注解等。了解如何配置和管理Bean是使用Spring框架的关键。通过掌握Bean的基础知识，你能够更灵活地配置和管理应用程序的组件。
- **生命周期管理：** Spring框架负责管理Bean的生命周期，包括实例化、初始化和销毁。理解Bean的生命周期管理是确保应用程序正确运行和资源正确释放的关键。
- **AOP和面向切面编程：** Spring框架提供了AOP（Aspect-Oriented Programming）支持，允许你通过切面将横切关注点（cross-cutting concerns）与主业务逻辑分离。Bean是AOP中的目标对象，理解Bean的基础知识是学习AOP的前提。
- **模块化和可维护性：** 通过使用Spring Bean，你可以将应用程序划分为独立的模块，每个模块由一个或多个Bean组成。这种模块化的设计增加了代码的可维护性，使得应用程序更易于理解和扩展。
- **测试：** Spring框架支持通过依赖注入和Bean的配置来进行单元测试。理解Bean的基础知识有助于编写可测试的代码，提高应用程序的质量。

因此，掌握Spring Bean的基础知识是学习和使用Spring框架的基础。这为构建松耦合、可维护和可测试的应用程序奠定了基础，同时也为学习和使用Spring框架的其他高级功能打下了基础。

## Spring Bean 配置元信息-BeanDefinition和它的派生类



> 常见的BeanDefinition实现包括：
>
> - GenricBeanDefinition
> - RootBeanDefinition
> - AnnotatedGenricBeanDefinition
>
> 其相关类 UML 图如下

![image-20231207230342917](https://img.markuszhang.com/img/image-20231207230342917.png)

### BeanDefinition 接口 API

我们通过源文件+注释的方式来阐述各个API的作用，如下所示：

```java

public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
	 * 作用域 单例
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 * 作用域 原型
	 */
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/**
	 * 表示当前 BeanDefinition 主要用于应用 Bean 配置元信息
	 */
	int ROLE_APPLICATION = 0;

	/**
	 * 表示当前 BeanDefinition 通常应用于一些大的外部化配置对象
	 */
	int ROLE_SUPPORT = 1;

	/**
	 * 表示当前 BeanDefinition 通常应用与框架基础设施
	 */
	int ROLE_INFRASTRUCTURE = 2;


	// Modifiable attributes

	/**
	 * 设置该 BeanDefinition 的父BeanDefinition的name
	 */
	void setParentName(@Nullable String parentName);

	/**
	 * 获取 父BeanDefinition名称
	 */
	@Nullable
	String getParentName();

	/**
	 * 指定当前 BeanDefinition 对应的 Bean 的 class 名称
	 */
	void setBeanClassName(@Nullable String beanClassName);

	/**
	 * 获取当前 bean 的 class 名称
	 */
	@Nullable
	String getBeanClassName();

	/**
	 * 设置 Bean 的作用域
	 */
	void setScope(@Nullable String scope);

	/**
	 * 获取 Bean 的作用域
	 */
	@Nullable
	String getScope();

	/**
	 * 设置 Bean 是否采取懒加载模式
	 */
	void setLazyInit(boolean lazyInit);

	/**
	 * 判断当前 Bean 是否是懒加载模式
	 */
	boolean isLazyInit();

	/**
	 * 设置当前 Bean 依赖的 Bean，保证这些依赖的 Bean 先完成初始化
	 */
	void setDependsOn(@Nullable String... dependsOn);

	/**
	 * 返回当前 Bean 依赖的 Bean
	 */
	@Nullable
	String[] getDependsOn();

	/**
	 * 设置当前 Bean 是否作为依赖注入的候选数据源
	 */
	void setAutowireCandidate(boolean autowireCandidate);

	/**
	 * 判断当前 Bean 是否是依赖注入的候选数据源
	 */
	boolean isAutowireCandidate();

	/**
	 * 当容器中注入多个同类的Bean实例时，在依赖查找或者依赖注入时，是否将当前Bean作为首要数据源
	 */
	void setPrimary(boolean primary);

	/**
	 * 判断当前 Bean 是否是首要的数据源
	 */
	boolean isPrimary();

	/**
	 * 设置 工厂Bean 名称
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);

	/**
	 * 返回 工厂Bean 名称
	 */
	@Nullable
	String getFactoryBeanName();

	/**
	 * 设置 工厂方法 名称
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);

	/**
	 * 返回 工厂方法 名称
	 */
	@Nullable
	String getFactoryMethodName();

	/**
	 * 返回当前 Bean 构造器参数元信息
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * 返回当前 Bean 是否有构造器参数元信息
	 * @since 5.0.2
	 */
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 * 返回当前 Bean 属性元信息
	 */
	MutablePropertyValues getPropertyValues();

	/**
	 * 返回当前 Bean 是否有属性元信息
	 * @since 5.0.2
	 */
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	/**
	 * 设置初始化方法名称
	 * @since 5.1
	 */
	void setInitMethodName(@Nullable String initMethodName);

	/**
	 * 返回初始化方法名称
	 * @since 5.1
	 */
	@Nullable
	String getInitMethodName();

	/**
	 * 设置销毁方法名称
	 * @since 5.1
	 */
	void setDestroyMethodName(@Nullable String destroyMethodName);

	/**
	 * 返回销毁方法名称
	 * @since 5.1
	 */
	@Nullable
	String getDestroyMethodName();

	/**
	 * 设置当前 Bean 的角色
	 * @since 5.1
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	void setRole(int role);

	/**
	 * 返回当前 Bean 的角色
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	int getRole();

	/**
	 * 对 BeanDefinition 设置一个可读的描述语句
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);

	/**
	 * 返回该 BeanDefinition 的描述
	 */
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * 返回当前 Bean 对应的类型
	 * @since 5.2
	 * @see ConfigurableBeanFactory#getMergedBeanDefinition
	 */
	ResolvableType getResolvableType();

	/**
	 * 是否是单例 Bean
	 * @see #SCOPE_SINGLETON
	 */
	boolean isSingleton();

	/**
	 * 是否是原型 Bean
	 * @since 3.0
	 * @see #SCOPE_PROTOTYPE
	 */
	boolean isPrototype();

	/**
	 * 标记当前 Bean 是否是抽象的
	 */
	boolean isAbstract();

	/**
	 * 返回当前 BeanDefinition 来源的描述，例如通过注解注入时所在的类或者是xml资源等
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * 获取最初的 BeanDefinition。类继承层次
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
```

我们还可以看到，BeanDefinition还继承了AttributeAccessor和BeanMetadataElement，这俩接口是什么作用呢？我们简单解释下：

- AttributeAccessor，它定义了一个通用契约，可以用于向任意对象附加和访问元数据
- BeanMetadataElement，定义当前对象的来源

我们通过一段代码示例演示两个接口的作用：根据两个接口的功能对 Bean 实例进行定制化

```java
package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: markus
 * @date: 2023/12/7 10:17 PM
 * @Description: BeanDefinition 元信息示例
 * @Blog: https://markuszhang.com
 * @see BeanDefinition
 * It's my honor to share what I've learned with you!
 */
public class BeanDefinitionMetadataDemo {
    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // PropertyValue
        beanDefinitionBuilder.addPropertyValue("username", "markus zhang");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // Attribute 附加属性 不会对 Bean 的实例化产生影响（除非定制化）
        beanDefinition.setAttribute("username", "张xx");
        // beanDefinition 的来源为 BeanDefinitionMetadataDemo
        beanDefinition.setSource(BeanDefinitionMetadataDemo.class);


        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (BeanDefinitionMetadataDemo.class.equals(beanDefinition.getSource())) {
                    if (bean instanceof User) {
                        String username = (String) beanDefinition.getAttribute("username");
                        ((User) bean).setUsername(username);
                    }
                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User bean = beanFactory.getBean(User.class);
        System.out.println(bean);
    }
}
```

### AnnotatedBeanDefinition 接口 API

AnnotatedBeanDefinition 继承自 BeanDefinition，扩展两个功能：

- 获取标注注解的元信息
- 获取工厂方法的元信息

源代码如下：

```java
public interface AnnotatedBeanDefinition extends BeanDefinition {

	/**
	 * 包含标注注解的元信息
	 */
	AnnotationMetadata getMetadata();

	/**
	 * 生产当前 Bean 的工厂方法（如果有的话）
	 * @since 4.1.1
	 */
	@Nullable
	MethodMetadata getFactoryMethodMetadata();

}
```

## 如何命名 Spring Bean

### Bean 的名称

每个 Bean 拥有一个或多个标识符，这些标识符在 Bean 所在的容器必须是唯一的。通常，一个 Bean 仅有一个标识符，如果需要额外的，可以使用别名（Alias）来扩充。

在基于 XML 的配置元信息中，开发人员可用 id 或者 name 属性来规定 Bean 的标识符。通过 Bean 的标识符由字母组成，允许出现特殊符号。如果想引入别名的话，可通过以下两种方式实现:

- 在 \<Bean\> 标签 name 属性使用半角逗号(",")或分号(";")来间隔
- 通过 \<Alias\> 来命名

我们通过一个示例来说明上述两种方式:

- Xml 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" name="user1,user2,user3" class="com.markus.spring.ioc.overview.domain.User">
        <property name="id" value="1"/>
        <property name="username" value="markus zhang"/>
    </bean>

    <!--给Bean实例定义别名-->
    <alias name="user" alias="user4"/>

</beans>
```

- Java 代码示例

```java
package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/1
 * @Description: 别名注册bean示例
 */
public class AliasBeanDefinitionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition.xml");
        // 通过别名获取 Bean 实例
        User user1 = beanFactory.getBean("user1", User.class);
        User user4 = beanFactory.getBean("user4", User.class);
        System.out.println(user1 == user4);
    }
}
```

### Bean 的名称生成器

Bean 的 id 或 name 属性并非必须指定，如果不指定的话，Spring 会为 Bean 自动生成一个唯一的名称。Bean 的命名尽管没有限制，不过官方建议采用驼峰的方式，更符合 Java 的命名约定。

Spring 通过BeanNameGenerator来实现 Spring Bean 名称的生成功能，该接口有两个实现类，分别如下：

- DefaultBeanNameGenerator :

    - 采用类的全限定名+#+序号的方式

    - ```java
    public static String uniqueBeanName(String beanName, BeanDefinitionRegistry registry) {
      String id = beanName;
      int counter = -1;
    
      // Increase counter until the id is unique. #1 #2 ...
      String prefix = beanName + GENERATED_BEAN_NAME_SEPARATOR;
      while (counter == -1 || registry.containsBeanDefinition(id)) {
        counter++;
        id = prefix + counter;
      }
      return id;
    }
    ```

- AnnotationBeanNameGenerator :

    - 采用类名(排除包路径名)并且使用驼峰风格

    - ```java
    protected String buildDefaultBeanName(BeanDefinition definition) {
      String beanClassName = definition.getBeanClassName();
      Assert.state(beanClassName != null, "No bean class name set");
      String shortClassName = ClassUtils.getShortName(beanClassName);
      return Introspector.decapitalize(shortClassName);
    }
    ```

## 注册 BeanDefinition 的几种方式

BeanDefinition的注册共有三种直接方式:

- XML 配置元信息
    - \<bean name="xx" ...\>
- Java 注解配置元信息
    - @Bean
    - @Component
    - @Import
- Java API 配置元信息
    - org.springframework.beans.factory.support.BeanDefinitionRegistry#registerBeanDefinition
    - org.springframework.beans.factory.support.BeanDefinitionReaderUtils#registerWithGeneratedName
    - org.springframework.context.annotation.AnnotatedBeanDefinitionReader#register

通常对我们框架应用人员来讲，XML 和 Java 注解的方式是流行的，实际上不论是 XML 还是 Java 注解的方式，其实对应的底层上，都是通过 Java API 进行注册的，我们对 Java API 做一个简单的示例:

```java
package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/30
 * @Description: BeanDefinition api使用示例
 */
public class AbstractBeanDefinitionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    beanDefinitionBuilder.addPropertyValue("id", 1L)
        .addPropertyValue("username", "markus zhang");
    AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    beanFactory.registerBeanDefinition("userByBuilder", beanDefinition);

    AbstractBeanDefinition beanDefinitionByManualCreation = new GenericBeanDefinition();
    beanDefinitionByManualCreation.setBeanClass(User.class);
    MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
    mutablePropertyValues.add("id", 2L)
        .add("username", "luna");
    beanDefinitionByManualCreation.setPropertyValues(mutablePropertyValues);
    beanFactory.registerBeanDefinition("userByManualCreation", beanDefinitionByManualCreation);

    Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
    System.out.println(beansOfType);
  }
}
```

上面是 我们创建 BeanDefinition 然后对 BeanDefinition 进行注册，下面还有一个针对外部单例对象注册的 Java API 注册方式:

- org.springframework.beans.factory.config.SingletonBeanRegistry#registerSingleton

![image-20231210171151255](https://img.markuszhang.com/img/image-20231210171151255.png)

## 实例化Bean的几种方式

### 常规方式

可以通过如下方式:

- 通过构造器
- 通过静态工厂方法
- 通过 Bean 工厂方法
- 通过 FactoryBean

通过构造器实例化 Bean 是再常规不过的了，Spring 底层默认也是通过这样的方式来进行实例化的，我们可以看下底层的源码，示例就不演示了。

源码位置 : org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance

![image-20231210171949427](https://img.markuszhang.com/img/image-20231210171949427.png)

下面通过一个示例分别演示下其余三种常规方式:

- XML 配置元信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--通过静态工厂方法-->
    <bean id="user-by-static-method" class="com.markus.spring.ioc.overview.domain.User" factory-method="createUser"/>

    <!--通过抽象工厂方法-->
    <bean id="userFactory" class="com.markus.spring.bean.factory.DefaultUserFactory"/>
    <bean id="user-by-instance-method" factory-bean="userFactory" factory-method="createUser"/>

    <!--通过FactoryBean-->
    <bean id="user-by-factory-bean" class="com.markus.spring.bean.factory.UserFactoryBean"/>

</beans>
```

- Java 函数主类

```java
package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description: Bean 实例化的几种方式
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        Map<String, User> userMapBeans = context.getBeansOfType(User.class);
        userMapBeans.forEach((k, v) -> System.out.println("bean name : [ " + k + " ]" + ", bean : [ " + v + " ]"));

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        User bean = beanFactory.createBean(User.class);
        System.out.println("AutowireCapableBeanFactory#createBean " + bean);

        context.close();
    }
}
```

示例源代码位置: [BeanInstantiationDemo.java](https://github.com/markuszcl99/guide-spring/blob/main/spring-bean/src/main/java/com/markus/spring/bean/definition/BeanInstantiationDemo.java)

### 特殊方式

除上述常规方法外，我们还可以利用如下方式进行实例化，通常这都是框架底层的方式:

- 通过 ServiceLoaderFactoryBean
- 通过 AutowiredCapableBeanFactory#createBean

AutowiredCapableBeanFactory#createBean 这个方法是 Spring 底层进行 Bean 实例创建的 API，我们会在 Bean 生命周期环节详细介绍，这里不赘述了，我们演示下 ServiceLoaderFactoryBean是如何实例化 Bean 的。

- XML 配置

    - ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    
        <bean id="serviceLoaderFactoryBean"
              class="org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean">
            <property name="serviceType" value="org.springframework.beans.factory.ObjectFactory"/>
        </bean>
    
    </beans>
    ```

- 在 classpath:/META-INF下增加 /Service/类的全限定名 文件，并在文件中增加 要被实例化的类的全限定名，如下图所示:

    - ![image-20231210172731879](https://img.markuszhang.com/img/image-20231210172731879.png)

- 最后再来看看执行类

    - ```java
    package com.markus.spring.bean.definition;
    
    import com.markus.spring.ioc.overview.domain.User;
    import org.springframework.beans.factory.BeanFactory;
    import org.springframework.beans.factory.ObjectFactory;
    import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
    import org.springframework.context.support.ClassPathXmlApplicationContext;
    
    import java.util.Iterator;
    import java.util.Map;
    import java.util.ServiceLoader;
    
    /**
     * @Author: zhangchenglong06
     * @Date: 2023/12/4
     * @Description: 特殊的Bean 实例化方式
     */
    public class SpecialBeanInstantiationDemo {
        public static void main(String[] args) throws Exception {
            // jdk 提供的实现
            demoServiceLoader();
    
            // spring 提供的实现
            demoServiceLoaderFactoryBean();
        }
    
        private static void demoServiceLoaderFactoryBean() throws Exception {
            BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
            ServiceLoader<ObjectFactory> serviceLoader =  beanFactory.getBean("serviceLoaderFactoryBean", ServiceLoader.class);
            displayServiceLoader(serviceLoader);
        }
    
        private static void demoServiceLoader() {
            ServiceLoader<ObjectFactory> objectFactoryServiceLoader = ServiceLoader.load(ObjectFactory.class);
            displayServiceLoader(objectFactoryServiceLoader);
        }
    
        private static void displayServiceLoader(ServiceLoader<ObjectFactory> serviceLoader) {
            Iterator<ObjectFactory> iterator = serviceLoader.iterator();
            while (iterator.hasNext()) {
                ObjectFactory objectFactory = iterator.next();
                System.out.println(objectFactory.getObject());
            }
        }
    }
    ```

ServiceLoader的使用示例非常简单，细节可以通过看它的 Java Doc 即可

![image-20231210173342385](https://img.markuszhang.com/img/image-20231210173342385.png)

## Bean的初始化与销毁

> 在 Spring 框架中，Bean 的初始化 和 销毁动作类似，因此我划分到一个标题内，方便演示示例

在 Spring 框架中，初始化 Bean 的方式有以下几种:

- @PostConstruct 注解标注的方法
- 实现 InitializingBean 接口的 afterPropertiesSet() 方法
- 自定义初始化方法
    - 又分为XML、注解、Java API（底层实现）三种方式

同样的，销毁 Bean 的方式也有上述几种，形式如下:

- @PreDestory 注解标注的方法
- 实现 DisposableBean 接口的 destroy() 方法
- 自定义销毁方法
    - 同样也分为 XML、注解、Java API（底层实现）三种方式



针对上述阐述的初始化和销毁的几种方式，我们集中展示一下示例代码:

> Ps: 我们通过注解的方式展示了，XML是同样的原理，对于 Java API 底层实现，我们后面定位到底层源码给大家看下即可

```java
package com.markus.spring.bean.definition;

import com.markus.spring.bean.factory.DefaultUserFactory;
import com.markus.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/5
 * @Description: bean 初始化示例
 */
@Configuration
public class BeanInitializationAndDestroyDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(BeanInitializationAndDestroyDemo.class);
    // 启动应用上下文
    applicationContext.refresh();
    System.out.println("应用上下文启动完成...");
    UserFactory bean = applicationContext.getBean(UserFactory.class);
    // 关闭应用上下文
    System.out.println("应用上下文准备关闭...");
    applicationContext.close();
    System.out.println("应用上下文关闭完成...");
  }
  /**
   * // @Lazy 懒加载，只有在应用程序使用（依赖注入或者依赖查找）到时才会触发Bean的初始化
   */
  @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
	//@Lazy
  public DefaultUserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
```

程序执行效果如下图所示:

![image-20231210174238157](https://img.markuszhang.com/img/image-20231210174238157.png)

在示例里面，可能大家也看到了 @Lazy 注解，它的作用就是 决定 Bean 是否是懒加载模式，即只有在 通过依赖查找或者依赖注入时，才会触发 Bean 的初始化，大家也可以在演示的时候，将这个注释解除，再执行一下看看。控制台输出则会是不同的效果

## Bean的垃圾回收

Bean 的垃圾回收，这是由 JVM 决定的，通常，在我们 IoC 容器关闭后，程序进行 GC 的时候则会将这部分由 Spring 管理的单例 Bean 对象所占用的内存进行回收。

大家也可以通过下面这个例子来感受一下 Bean 被回收的过程。

```java
package com.markus.spring.bean.definition;

import com.markus.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/5
 * @Description: Bean的垃圾回收
 */
public class BeanGarbageCollectionDemo {
  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(BeanInitializationAndDestroyDemo.class);
    applicationContext.refresh();

    Thread.sleep(5000);
    applicationContext.close();
    // 手动触发Java垃圾回收机制，释放不再使用的对象内存空间。
    System.gc();
    Thread.sleep(5000);
  }
}
```

## 本文总结

上面，我们介绍了 Spring Bean 的基础知识，包括 BeanDefinition 接口信息、BeanDefinition的注册、Bean 名称的生成、Bean 的实例化、初始化、销毁、懒加载及其垃圾回收过程。它们贯穿Spring IoC 容器生命周期的始终，也是 学习和掌握 Spring 框架的重中之重。大家可以通读一遍，后面我们在讲述 Bean 的生命周期、IoC 容器的生命周期等原理内容时会反复强调这部分内容。

