package com.markus.spring.aop.features.aspect;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description:
 */
public class AspectXmlConfig {

  private void methodPointcut() {
  }

  public void beforeMethod() {
    System.out.println("before invoke method");
  }
}
