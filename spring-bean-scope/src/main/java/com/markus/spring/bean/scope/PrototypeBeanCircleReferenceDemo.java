package com.markus.spring.bean.scope;

import com.markus.spring.bean.scope.domain.A;
import com.markus.spring.bean.scope.domain.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/18
 * @Description:
 */
@Configuration
public class PrototypeBeanCircleReferenceDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(PrototypeBeanCircleReferenceDemo.class);

    context.refresh();

    A aBean = context.getBean(A.class);
    System.out.println(aBean);

    context.close();
  }

  @Bean
  @Scope("prototype")
  public A a(@Autowired B b) {
    A a = new A();
    a.setB(b);
    return a;
  }

  @Bean
  @Scope("prototype")
  public B b(@Autowired A a) {
    B b = new B();
    b.setA(a);
    return b;
  }
}
