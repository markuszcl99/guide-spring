package com.markus.spring.aop.features;

import com.markus.spring.aop.features.aspect.AspectAnnotationConfig;
import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description: 基于注解的 AOP 示例
 */
@EnableAspectJAutoProxy // 开启自动代理
public class AspectJAnnotationDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AspectJAnnotationDemo.class, AspectAnnotationConfig.class);
    context.refresh();

    EchoService echoService = context.getBean("echoService", EchoService.class);
    System.out.println(echoService.echo("Hello,AOP"));

    context.close();
  }

  @Bean
  public EchoService echoService() {
    return new DefaultEchoService();
  }

}
