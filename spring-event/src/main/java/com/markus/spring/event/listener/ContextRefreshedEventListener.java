package com.markus.spring.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/3/25
 * @Description:
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    System.out.println(event);
  }
}
