package com.markus.spring.expression.language.reference;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 9:55 PM
 * @Description: 方法调用表达式的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MethodInvokeExpression {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        String subString = parser.parseExpression("'Hello,SpEL'.substring(0,5)").getValue(String.class);
        System.out.println(subString);
    }
}
