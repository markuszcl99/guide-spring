package com.markus.spring.annotation;

import com.markus.spring.annotation.config.AnnotationTestConfig;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/5/13
 * @Description:
 */
public class AtOperatorScanAnnotationTestImportDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AnnotationTestConfig.class);
    context.refresh();

    User user = context.getBean(User.class);
    System.out.println(user);

    context.close();
  }
}
