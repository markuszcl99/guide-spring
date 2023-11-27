package com.markus.spring.ioc.overview.dependency.lookup;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/27
 * @Description: 依赖查找示例
 */
public class DependencyLookupDemo {

  /**
   * 知识点 <p>
   * 1. `BeanFactory`：是Spring框架中最核心的接口，它提供了高级的IoC（控制反转）功能。`BeanFactory`是一个工厂模式的实现，它使用控制反转来将应用程序的配置和依赖性规范与实际的应用程序代码分离。
   * <p>
   * 2. `FactoryBean`：是一个创建对象的工厂Bean，其主要作用是产生其他bean实例。Spring中许多内置的服务就是通过`FactoryBean`来提供的。当一个bean被标识为一个`FactoryBean`，Spring IoC容器就会在获取bean时，自动调用其`getObject`方法，将其返回的对象当做该bean的实例。
   * <p>
   * 3. `ObjectFactory`：是一个返回（通常是prototype）bean的工厂。这是一个策略接口，主要用于延迟依赖注入。与`FactoryBean`不同的是，`ObjectFactory`是在每次调用`getObject()`时返回一个新的实例，而`FactoryBean`只在第一次调用`getObject()`时返回一个新的实例，之后都返回同一个实例。
   * <p>
   * 总结：`BeanFactory`是一个底层的工厂接口，`FactoryBean`和`ObjectFactory`都是在其基础上的扩展和补充，提供了更为灵活和丰富的功能。
   */
  public static void main(String[] args) throws Exception {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");

    // 1. 实时查找
    realtimeLookup(beanFactory);
    // 2. 延迟查找
    lazyLookup(beanFactory);
  }

  /**
   * 实时查找
   */
  private static void realtimeLookup(BeanFactory beanFactory) {
    User user = beanFactory.getBean("user", User.class);
    System.out.println("实时查找: " + user);
  }

  /**
   * 延迟查找
   */
  private static void lazyLookup(BeanFactory beanFactory) throws Exception {
    @SuppressWarnings("unchecked")
    ObjectFactory<User> factoryBean = (ObjectFactory<User>) beanFactory.getBean("factoryBean");
    System.out.println("延迟查找: " + factoryBean.getObject());
  }
}
