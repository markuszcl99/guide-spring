package com.markus.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public class JdkDynamicProxyDemo {
  public static void main(String[] args) {
    // 被代理对象
    EchoService echoService = new DefaultEchoService();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    EchoService proxy = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (EchoService.class.isAssignableFrom(proxy.getClass())) {
          long startTime = System.currentTimeMillis();
          Object result = method.invoke(echoService, args);
          long endTime = System.currentTimeMillis();
          System.out.println("echo invoke cost [" + (endTime - startTime) + "] ms");
          return result;
        }
        return null;
      }
    });

    System.out.println(proxy.echo("Hello,Jdk Dynamic Proxy"));

  }
}
