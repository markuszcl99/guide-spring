package com.markus.spring.event;

import com.markus.spring.event.listener.ContextRefreshedEventListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/3/25
 * @Description:
 */
@Configuration
public class ApplicationEventListenerDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(ApplicationEventListenerDemo.class);
    context.addApplicationListener(new ContextRefreshedEventListener());
    context.refresh();

    context.close();
  }
}
