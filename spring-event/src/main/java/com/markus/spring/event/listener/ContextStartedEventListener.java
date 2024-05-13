package com.markus.spring.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/3/26
 * @Description:
 */
public class ContextStartedEventListener implements ApplicationListener<ContextStartedEvent> {
  @Override
  public void onApplicationEvent(ContextStartedEvent event) {
    System.out.println(event);
  }
}
