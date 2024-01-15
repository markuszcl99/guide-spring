package com.markus.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public interface MethodAfterReturningInterceptor {
  /**
   * 在目标方法正常返回后拦截
   *
   * @param proxy
   * @param method
   * @param args
   * @param returnValue
   * @return
   */
  Object afterReturning(Object proxy, Method method, Object[] args, Object returnValue);
}
