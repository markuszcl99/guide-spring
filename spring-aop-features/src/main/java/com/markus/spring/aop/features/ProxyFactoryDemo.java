package com.markus.spring.aop.features;

import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description: 基于底层 API {@link ProxyFactory} 的 AOP 示例
 * @see ProxyFactory
 */
public class ProxyFactoryDemo {
  public static void main(String[] args) {
    EchoService echoService = new DefaultEchoService();
    ProxyFactory proxyFactory = new ProxyFactory(echoService);
    proxyFactory.addAdvice(new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("intercept method : " + method);
      }
    });

    EchoService proxy = (EchoService) proxyFactory.getProxy();
    System.out.println(proxy.echo("Hello,AOP"));
  }
}
