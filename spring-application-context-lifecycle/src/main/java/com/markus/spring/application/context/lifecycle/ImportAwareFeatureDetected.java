package com.markus.spring.application.context.lifecycle;

import com.markus.spring.application.context.lifecycle.config.BeanConfiguration;
import com.markus.spring.application.context.lifecycle.config.BeanConfiguration3;
import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: markus
 * @date: 2024/5/15 12:33 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ImportAwareFeatureDetected {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfiguration.class);
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        System.out.println("=========================start=========================");
        EchoService proxyEchoService = context.getBean("proxyEchoService", EchoService.class);
        proxyEchoService.echo("proxyEchoService");
        System.out.println("=========================end=========================");
        System.out.println("=========================start=========================");
        EchoService noProxyEchoService = context.getBean("noProxyEchoService", EchoService.class);
        noProxyEchoService.echo("noProxyEchoService");
        System.out.println("=========================end=========================");
        context.close();
    }
}
