package com.markus.spring.dependency.injection.bug;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description:
 */
public class DependencyInjectionBugDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // register configuration class
    context.register(BeanConfigA.class, BeanConfigB.class);

    // refresh spring context
    context.refresh();

    Map<String, UserEntity> beansOfType = context.getBeansOfType(UserEntity.class);
    System.out.println("ioc container include : ");
    beansOfType.forEach((name, bean) -> System.out.println(name + ": " + bean.getUsername()));

    // The Spring Bean into which the userEntities field in BeanConfigA is injected is expected to contain User A1、User A2、User B1、User B2
    BeanConfigA beanConfigA = context.getBean(BeanConfigA.class);
    System.out.println(beanConfigA.getUserEntities());

    // The Spring Bean into which the userEntities field in BeanConfigB is injected is expected to contain User A1、User A2、User B1、User B2
    BeanConfigB beanConfigB = context.getBean(BeanConfigB.class);
    System.out.println(beanConfigB.getUserEntities());

    // close spring context
    context.close();
  }
}
