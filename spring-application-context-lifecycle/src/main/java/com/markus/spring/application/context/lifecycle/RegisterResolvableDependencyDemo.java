package com.markus.spring.application.context.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/10
 * @Description: 注册 可解析依赖 示例
 * @see ConfigurableListableBeanFactory#registerResolvableDependency(Class, Object)
 */
public class RegisterResolvableDependencyDemo {

  @Autowired
  private ApplicationContext applicationContext;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(RegisterResolvableDependencyDemo.class);

    context.refresh();

//    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//    beanFactory.registerResolvableDependency(ApplicationContext.class, new AnnotationConfigApplicationContext());

    RegisterResolvableDependencyDemo registerResolvableDependencyDemo = context.getBean(RegisterResolvableDependencyDemo.class);

    System.out.println("context == registerResolvableDependencyDemo.applicationContext : " + (context == registerResolvableDependencyDemo.applicationContext));

    context.close();
  }
}
