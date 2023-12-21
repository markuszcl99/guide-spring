package com.markus.spring.dependency.lookup;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author: markus
 * @date: 2023/12/17 10:54 PM
 * @Description: Spring 内建依赖的 依赖查找示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SpringInternalBeanDependencyLookDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");

        displaySpringInternalBean(context, Environment.class);
        displaySpringInternalBean(context, Properties.class);
        displaySpringInternalBeanByName(context, "systemEnvironment");
        displaySpringInternalBean(context, MessageSource.class);
        displaySpringInternalBean(context, LifecycleProcessor.class);
        displaySpringInternalBean(context, ApplicationEventMulticaster.class);

        // 关闭 Spring 容器上下文
        context.close();

        // 基于 注解驱动 的应用上下文
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(SpringInternalBeanDependencyLookDemo.class);
        annotationConfigApplicationContext.refresh();

        displaySpringInternalBean(annotationConfigApplicationContext, ConfigurationClassPostProcessor.class);
        displaySpringInternalBean(annotationConfigApplicationContext, AutowiredAnnotationBeanPostProcessor.class);
        displaySpringInternalBean(annotationConfigApplicationContext, CommonAnnotationBeanPostProcessor.class);
        displaySpringInternalBean(annotationConfigApplicationContext, EventListenerMethodProcessor.class);
        displaySpringInternalBean(annotationConfigApplicationContext, DefaultEventListenerFactory.class);

        annotationConfigApplicationContext.close();
    }

    private static void displaySpringInternalBean(ApplicationContext context, Class<?> type) {
        Object bean = context.getBean(type);
        System.out.println(bean);
    }

    private static void displaySpringInternalBeanByName(ApplicationContext context, String beanName) {
        Object bean = context.getBean(beanName);
        System.out.println(bean);
    }
}
