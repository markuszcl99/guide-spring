package com.markus.spring.aop.features;

import com.markus.spring.aop.features.interceptor.EchoServiceInterceptor;
import com.markus.spring.aop.features.pointcut.EchoServiceStaticMethodMatcherPointcut;
import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * @author: markus
 * @date: 2024/1/16 11:17 PM
 * @Description: Pointcut API 示例
 * @Blog: https://markuszhang.com
 * @see Pointcut
 * @see StaticMethodMatcherPointcut
 * @see ProxyFactory
 * It's my honor to share what I've learned with you!
 */
public class PointcutAPIDemo {
    public static void main(String[] args) {
        EchoServiceStaticMethodMatcherPointcut pointcut = new EchoServiceStaticMethodMatcherPointcut("echo", EchoService.class);

        ProxyFactory proxyFactory = new ProxyFactory(new DefaultEchoService());

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceInterceptor());
        proxyFactory.addAdvisor(advisor);

        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,AOP"));
    }

}
