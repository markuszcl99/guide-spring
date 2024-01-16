package com.markus.spring.aop.features.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description:
 */
public class EchoServiceInterceptor implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println(invocation.getMethod());
    return invocation.proceed();
  }
}
