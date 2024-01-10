package com.markus.spring.annotation;

import com.markus.spring.annotation.config.BeanConfig;
import com.markus.spring.annotation.config.BeanConfig2;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class AtConfigurationAnnotationDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(BeanConfig.class);

    applicationContext.refresh();

    System.out.println("==============================");
    Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
    beansOfType.forEach((key, value) -> {
      System.out.println(key + ": " + value);
    });
    System.out.println("==============================");

    applicationContext.close();
  }
}
