package com.markus.spring.aop.features;

import com.markus.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description: 基于 ProxyFactoryBean 的 AOP 示例
 */
public class ProxyFactoryBeanDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");


    EchoService echoService = context.getBean("proxyFactoryBean", EchoService.class);
    System.out.println(echoService.echo("Hello,Spring AOP"));
    context.close();
  }
}
