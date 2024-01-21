package com.markus.spring.expression.language.reference;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 8:46 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class LiteralExpressions {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        // 解析 字符串
        String value = parser.parseExpression("'Hello,Literal Expression'").getValue(String.class);
        System.out.println(value);
        // 解析 数值 int long float double
        double number = parser.parseExpression("4.5").getValue(double.class);
        System.out.println(number);
        // 解析 布尔值 true or false
        boolean bool = parser.parseExpression("true").getValue(boolean.class);
        System.out.println(bool);
        // 解析 空对象 null
        Object obj = parser.parseExpression("null").getValue();
        System.out.println(obj);
    }
}
