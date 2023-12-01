package com.markus.spring.bean.definition;

import com.markus.spring.bean.selector.MyImportBeanDefinitionRegistrar;
import com.markus.spring.bean.selector.MyImportSelector;
import com.markus.spring.ioc.overview.dependency.lookup.DependencyLookupDemo;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/1
 * @Description: 利用注解驱动，实现BeanDefinition注册到IoC容器中的几种示例
 * @see DependencyLookupDemo xml驱动 完成 BeanDefinition 的注册
 * @see AbstractBeanDefinitionDemo 底层api支持
 * 本类主要利用注解完成BeanDefinition的注册
 */
@ComponentScan("com.markus.spring.bean.definition")
@Import({AnnotationBeanDefinitionDemo.ConfigC.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
// 知识点：ImportSelector和ImportBeanDefinitionRegistrar都是与@Import注解一起使用的接口，用于动态导入配置。
public class AnnotationBeanDefinitionDemo {
  /**
   * 总结:<p>
   * 第一种方式: 通过 @Bean 注解完成Bean注册，例如本示例中的User实例<p>
   * 第二种方式: 通过 @Component 及其派生注解完成Bean注册，例如@Configuration，注意在使用该注解的时候，记得在配置类上标注@ComponentScan指定下扫描目录<p>
   * 第三种方式: 通过 @Import将 指定的类导入进来，可以是普通类，也可以是实现MyImportSelector、MyImportBeanDefinitionRegistrar的类<p>
   */
  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册配置类（configuration class）
    applicationContext.register(AnnotationBeanDefinitionDemo.class);
    // 启动上下文
    applicationContext.refresh();

    Map<String, User> normalBeans = applicationContext.getBeansOfType(User.class);
    System.out.println(normalBeans);

    Map<String, IConfig> configBeans = applicationContext.getBeansOfType(IConfig.class);
    System.out.println(configBeans);

    // 关闭上下文
    applicationContext.close();
  }

  public interface IConfig {
  }

  @Configuration
  public static class Config implements IConfig {

    @Bean(name = {"user", "markuszhang"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setUsername("markus zhang");
      return user;
    }
  }

  public static class ConfigA implements IConfig {
    @Bean(name = {"userA"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setUsername("markus zhangA");
      return user;
    }
  }

  public static class ConfigB implements IConfig {
    @Bean(name = {"userB"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setUsername("markus zhangB");
      return user;
    }
  }

  public static class ConfigC implements IConfig {
    @Bean(name = {"userC"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setUsername("markus zhangC");
      return user;
    }
  }
}
