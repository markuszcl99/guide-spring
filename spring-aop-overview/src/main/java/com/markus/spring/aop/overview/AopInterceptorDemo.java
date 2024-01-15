package com.markus.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description: AOP 拦截示例
 */
public class AopInterceptorDemo {
  public static void main(String[] args) {
    // 被代理对象
    EchoService echoService = new DefaultEchoService();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    EchoService proxy = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (EchoService.class.isAssignableFrom(proxy.getClass())) {
          MethodBeforeInterceptor methodBeforeInterceptor = new MethodBeforeInterceptor() {
            @Override
            public Object before(Object proxy, Method method, Object[] args) {
              return System.currentTimeMillis();
            }
          };
          MethodAfterReturningInterceptor afterReturningInterceptor = new MethodAfterReturningInterceptor() {
            @Override
            public Object afterReturning(Object proxy, Method method, Object[] args, Object returnValue) {
              return System.currentTimeMillis();
            }
          };

          long startTime = 0L;
          long endTime = 0L;
          try {
            startTime = (long) methodBeforeInterceptor.before(proxy, method, args);
            Object result = method.invoke(echoService, args);
            endTime = (long) afterReturningInterceptor.afterReturning(proxy, method, args, result);
            return result;
          } catch (Exception e) {
            MethodAfterThrowingInterceptor methodAfterThrowingInterceptor = new MethodAfterThrowingInterceptor() {
              @Override
              public void afterThrowing(Object proxy, Method method, Object[] args, Throwable throwable) {
                System.err.printf("exception is \n %s\n", throwable);
              }
            };
            methodAfterThrowingInterceptor.afterThrowing(proxy, method, args, e);
          } finally {
            MethodAfterInterceptor methodAfterInterceptor = new MethodDuration(startTime, endTime);
            long costTime = (long) methodAfterInterceptor.after(proxy, method, args);
            System.out.println("target method cost time [" + costTime + "] ms");
          }
        }
        return null;
      }
    });

    System.out.println(proxy.echo("Hello,Jdk Dynamic Proxy"));

  }
}

class MethodDuration implements MethodAfterInterceptor {

  private final long startTime;
  private final long endTime;

  MethodDuration(long startTime, long endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Object after(Object proxy, Method method, Object[] args) {
    return endTime - startTime;
  }
}
