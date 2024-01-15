package com.markus.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description: 最终执行拦截
 */
public interface MethodAfterInterceptor {
  /**
   * 在目标方法执行完成后拦截（不管异常抛出还是正常返回）
   *
   * @param proxy
   * @param method
   * @param args
   * @return
   */
  Object after(Object proxy, Method method, Object[] args);
}
