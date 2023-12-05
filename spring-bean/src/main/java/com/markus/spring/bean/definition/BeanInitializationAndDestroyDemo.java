package com.markus.spring.bean.definition;

import com.markus.spring.bean.factory.DefaultUserFactory;
import com.markus.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    UserFactory bean = applicationContext.getBean(UserFactory.class);
    // 关闭应用上下文
    applicationContext.close();
  }

  @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
  public DefaultUserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
