package com.markus.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2024/5/14 12:16 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class BeanFactoryPostProcessorDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanFactoryPostProcessorDemo.class);
        context.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());

        context.refresh();
        context.close();
    }
}
