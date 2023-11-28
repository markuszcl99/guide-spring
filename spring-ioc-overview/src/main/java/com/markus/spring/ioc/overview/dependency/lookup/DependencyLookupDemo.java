package com.markus.spring.ioc.overview.dependency.lookup;

import com.markus.spring.ioc.overview.annotation.Super;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/27
 * @Description: 依赖查找示例
 */
public class DependencyLookupDemo {

  /**
   * 知识点一: <p>
   * 1. `BeanFactory`：是Spring框架中最核心的接口，它提供了高级的IoC（控制反转）功能。`BeanFactory`是一个工厂模式的实现，它使用控制反转来将应用程序的配置和依赖性规范与实际的应用程序代码分离。
   * <p>
   * 2. `FactoryBean`：是一个创建对象的工厂Bean，其主要作用是产生其他bean实例。Spring中许多内置的服务就是通过`FactoryBean`来提供的。当一个bean被标识为一个`FactoryBean`，Spring IoC容器就会在获取bean时，自动调用其`getObject`方法，将其返回的对象当做该bean的实例。
   * <p>
   * 3. `ObjectFactory`：是一个返回（通常是prototype）bean的工厂。这是一个策略接口，主要用于延迟依赖注入。与`FactoryBean`不同的是，`ObjectFactory`是在每次调用`getObject()`时返回一个新的实例，而`FactoryBean`只在第一次调用`getObject()`时返回一个新的实例，之后都返回同一个实例。
   * <p>
   * 总结：`BeanFactory`是一个底层的工厂接口，`FactoryBean`和`ObjectFactory`都是在其基础上的扩展和补充，提供了更为灵活和丰富的功能。
   * <p>
   * 知识点二: <p>
   * 延迟查找主要应用于以下几种场景：
   * <p>
   * 1. 资源消耗较大的Bean：对于一些初始化或运行消耗较大的Bean，如果不是每次都需要用到，可以使用延迟查找，只有在真正需要的时候才去创建和初始化。
   * <p>
   * 2. 依赖关系复杂的Bean：对于一些依赖关系复杂，需要在运行时动态决定依赖关系的Bean，可以使用延迟查找。
   * <p>
   * 3. 生命周期特殊的Bean：对于一些生命周期不由Spring容器管理，或者需要在特定时机进行初始化的Bean，可以使用延迟查找。
   * <p>
   * 4. Prototype类型的Bean：对于Prototype类型的Bean，每次获取都会创建一个新的实例，如果使用延迟查找，可以更好地控制Bean的创建时机和数量。
   */
  public static void main(String[] args) throws Exception {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");

    // 1. 实时查找
    realtimeLookup(beanFactory);
    System.out.println("========================");
    // 2. 延迟查找
    lazyLookup(beanFactory);
    System.out.println("========================");
    // 3. 按照类型查找单个Bean
    typeLookup(beanFactory);
    System.out.println("========================");
    // 4. 按照类型查找多个Bean
    collectionLookup(beanFactory);
    System.out.println("========================");
    // 5. 按照注解类型查找集合Bean
    annotationLookup(beanFactory);
  }


  /**
   * ========================按照 Bean 名称查找========================
   */

  /**
   * 实时查找
   */
  private static void realtimeLookup(BeanFactory beanFactory) {
    // 名称+类型
    User user = beanFactory.getBean("user", User.class);
    System.out.println("实时查找: " + user);
  }

  /**
   * 延迟查找
   */
  private static void lazyLookup(BeanFactory beanFactory) throws Exception {
    @SuppressWarnings("unchecked")
    ObjectFactory<User> factoryBean = (ObjectFactory<User>) beanFactory.getBean("factoryBean");
    System.out.println("延迟生效中....");
    System.out.println("延迟查找: " + factoryBean.getObject());
  }

  /**
   * ========================按照 Bean 类型查找========================
   */

  /**
   * 单个Bean类型查找
   *
   * @param beanFactory
   */
  private static void typeLookup(BeanFactory beanFactory) {
    User user = beanFactory.getBean(User.class);
    System.out.println(user);
  }

  /**
   * 根据集合类型查找
   */
  private static void collectionLookup(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
      userMap.forEach((beanName, user) -> System.out.println("Bean name: " + beanName + ", User: " + user));
    }
  }

  /**
   * ========================按照注解查找========================
   */
  /**
   * 根据注解查找集合Bean
   *
   * @param beanFactory
   */
  private static void annotationLookup(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
      beansWithAnnotation.forEach((beanName, bean) -> System.out.println("Bean name: " + beanName + ", Bean: " + bean));
    }
  }
}
