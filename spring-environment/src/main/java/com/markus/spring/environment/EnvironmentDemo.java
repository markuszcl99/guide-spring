package com.markus.spring.environment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author: markus
 * @date: 2024/1/21 5:34 PM
 * @Description: {@link Environment} 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class EnvironmentDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentDemo.class);
        context.refresh();

        Environment environment = context.getEnvironment();
        System.out.println(environment);

        context.close();
    }
}
