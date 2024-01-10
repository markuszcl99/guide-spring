package com.markus.spring.annotation;

import com.markus.spring.annotation.config.BeanConfig2;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class AtBeanFactoryBeanNameDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(BeanConfig2.class);

    applicationContext.refresh();

//    BeanConfig2 beanConfig2 = applicationContext.getBean(BeanConfig2.class);
//    User manualInvoke = beanConfig2.user2();
//    User lookup = applicationContext.getBean("user2", User.class);
//    System.out.println("beanConfig2 is " + beanConfig2);
//    System.out.println("manualInvoke == lookup : " + (manualInvoke == lookup));

    User user = applicationContext.getBean(User.class);
    BeanConfig2 bean = applicationContext.getBean(BeanConfig2.class);

    applicationContext.close();
  }
}
