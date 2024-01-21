package com.markus.spring.expression.language;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.annotation.Annotation;

/**
 * @author: markus
 * @date: 2024/1/21 4:33 PM
 * @Description: {@link StandardEvaluationContext} 示例
 * @Blog: https://markuszhang.com
 * @see StandardEvaluationContext
 * It's my honor to share what I've learned with you!
 */
public class StandardEvaluationContextDemo {
    public static void main(String[] args) {
        // 这里只展示 StandardEvaluationContext 的特有特性示例（相较于 SimpleEvaluationContext）

        // 创建 SpEL 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 特性一 : java 类型引用
        StandardEvaluationContext context = new StandardEvaluationContext();
        //          使用 SpEL 获取 String 类型的引用
        Class<?> stringType = parser.parseExpression("T(java.lang.String)").getValue(context, Class.class);
        System.out.println("String Type : " + stringType);

        // 特性二 : 构造函数引用
        String value = parser.parseExpression("new String('Hello,SpEL')").getValue(context, String.class);
        System.out.println("constructed String : " + value);

        // 特性三 : Bean 引用 (可以是容器，也可以是一个普通的 root object)
        //          // 采用 容器
        context.setBeanResolver(new BeanFactoryResolver(getApplicationContext().getBeanFactory()));
        String name = parser.parseExpression("@inventor.name").getValue(context, String.class);
        System.out.println(name);

        //          // 采用 普通对象
        Inventor inventor = new Inventor();
        inventor.setName("markus zhang from pojo");
        context = new StandardEvaluationContext(inventor);
        name = parser.parseExpression("name").getValue(context, String.class);
        System.out.println(name);
    }

    private static AnnotationConfigApplicationContext getApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(StandardEvaluationContextDemo.class);
        context.refresh();
        return context;
    }

    @Bean("inventor")
    public Inventor inventor() {
        Inventor inventor = new Inventor();
        inventor.setName("markus zhang");
        return inventor;
    }
}
