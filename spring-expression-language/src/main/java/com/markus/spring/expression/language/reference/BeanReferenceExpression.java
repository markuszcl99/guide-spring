package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author: markus
 * @date: 2024/1/21 11:00 PM
 * @Description: Bean 应用示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanReferenceExpression {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/expression-in-bean-definitions.xml");

        BeanFactoryResolver beanFactoryResolver = new BeanFactoryResolver(context.getBeanFactory());
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(beanFactoryResolver);

        ExpressionParser parser = new SpelExpressionParser();
        String expression = "@inventor";
        Inventor inventor = parser.parseExpression(expression).getValue(evaluationContext, Inventor.class);
        System.out.println(inventor);
    }
}
