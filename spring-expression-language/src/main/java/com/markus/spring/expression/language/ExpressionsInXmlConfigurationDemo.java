package com.markus.spring.expression.language;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2024/1/21 5:22 PM
 * @Description: 在 Spring XML 文件中使用 SpEL 的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ExpressionsInXmlConfigurationDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/expression-in-bean-definitions.xml");

        Inventor inventor = context.getBean("inventor", Inventor.class);
        String name = inventor.getName();
        System.out.println(inventor);

        Inventor inventor1 = context.getBean("inventor2", Inventor.class);
        System.out.println(inventor1);
    }
}
