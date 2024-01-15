package com.markus.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public interface MethodAfterThrowingInterceptor {
  /**
   * 在目标方法抛出异常后拦截
   *
   * @param proxy
   * @param method
   * @param args
   * @param throwable
   * @return
   */
  void afterThrowing(Object proxy, Method method, Object[] args, Throwable throwable);
}
