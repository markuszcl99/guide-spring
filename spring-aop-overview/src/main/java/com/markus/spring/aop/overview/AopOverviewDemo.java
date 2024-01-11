package com.markus.spring.aop.overview;

import com.markus.spring.aop.overview.config.AopBeanConfig;
import com.markus.spring.aop.overview.service.AService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/10
 * @Description:
 */
public class AopOverviewDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AopBeanConfig.class);

    context.refresh();

    AService bean = context.getBean(AService.class);
    bean.sayHello();

    context.close();
  }
}
