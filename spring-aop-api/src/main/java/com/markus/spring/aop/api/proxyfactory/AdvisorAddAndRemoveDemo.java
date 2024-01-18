package com.markus.spring.aop.api.proxyfactory;

import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/18
 * @Description: 在 Advised 中操作 Advisor 的添加和删除操作示例
 */
public class AdvisorAddAndRemoveDemo {

  public static void main(String[] args) {
    ProxyFactory proxyFactory = new ProxyFactory(new DefaultEchoService());

    proxyFactory.addAdvice(new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("proxy method : " + method.getName());
      }
    });

    Advice afterReturningAdvice = new AfterReturningAdvice() {
      @Override
      public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method return value : " + returnValue);
      }
    };
    proxyFactory.addAdvice(afterReturningAdvice);

    EchoService proxy = (EchoService) proxyFactory.getProxy();
    // first invoke target method
    System.out.println(proxy.echo("Hello,World"));
    System.out.println("==============================");
    // remove after returning advice
    proxyFactory.removeAdvice(afterReturningAdvice);
    // second invoke target method
    System.out.println(proxy.echo("Hello,World"));
  }
}
