package com.markus.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public interface MethodBeforeInterceptor {
  /**
   * 在目标方法前拦截
   * @param proxy
   * @param method
   * @param args
   * @return
   */
  Object before(Object proxy, Method method, Object[] args);
}
