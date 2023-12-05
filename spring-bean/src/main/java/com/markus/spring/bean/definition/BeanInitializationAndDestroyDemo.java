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
//  @Lazy
  public DefaultUserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
