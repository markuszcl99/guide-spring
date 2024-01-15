package com.markus.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description: Cglib 动态代理示例
 */
public class CglibDynamicProxyDemo {
  public static void main(String[] args) {

    // 创建 被代理 类
    DefaultEchoService echoService = new DefaultEchoService();

    Enhancer enhancer = new Enhancer();
    // 设置 代理类
    enhancer.setSuperclass(DefaultEchoService.class);
    enhancer.setClassLoader(Thread.currentThread().getContextClassLoader());
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (EchoService.class.isAssignableFrom(proxy.getClass())) {
          long startTime = System.currentTimeMillis();
          Object result = methodProxy.invoke(echoService, objects);
          long endTime = System.currentTimeMillis();
          System.out.println("echo invoke cost [" + (endTime - startTime) + "] ms");
          return result;
        }
        return null;
      }
    });

    EchoService proxy = (EchoService) enhancer.create();
    System.out.println(proxy.echo("Hello,Cglib Dynamic Proxy"));
  }
}
