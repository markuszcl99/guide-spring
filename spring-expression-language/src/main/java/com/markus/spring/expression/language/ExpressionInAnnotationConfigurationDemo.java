package com.markus.spring.expression.language;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: markus
 * @date: 2024/1/21 5:46 PM
 * @Description: SpEL 在 注解驱动应用上下文 的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ExpressionInAnnotationConfigurationDemo {

    @Value("#{systemProperties['user.name']}")
    private String username;

    @Value("#{systemEnvironment['HOME']}")
    private String path;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ExpressionInAnnotationConfigurationDemo.class);
        context.refresh();

        ExpressionInAnnotationConfigurationDemo bean = context.getBean(ExpressionInAnnotationConfigurationDemo.class);
        System.out.println(bean.username);
        System.out.println(bean.path);

        context.close();
    }
}
