package com.markus.spring.application.context.lifecycle;

import org.springframework.context.LifecycleProcessor;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/10
 * @Description:
 */
public class MyLifeCycleProcessor implements LifecycleProcessor {
  @Override
  public void onRefresh() {
    System.out.println();
  }

  @Override
  public void onClose() {

  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
