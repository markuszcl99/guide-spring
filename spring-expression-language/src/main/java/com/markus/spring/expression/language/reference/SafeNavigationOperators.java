package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

/**
 * @author: markus
 * @date: 2024/1/21 11:11 PM
 * @Description: 安全指针示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SafeNavigationOperators {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        Inventor inventor = new Inventor();
        inventor.setName("markus zhang");
        context.setVariable("inventor", inventor);

        String name = parser.parseExpression("#inventor.name?.#inventor.name").getValue(context, String.class);
        System.out.println(name);  // markus zhang

        inventor.setName(null);
        name = parser.parseExpression("#inventor.name?.#inventor.name").getValue(context, String.class);
        System.out.println(name);  // null - does not throw NullPointerException!!!
    }
}
