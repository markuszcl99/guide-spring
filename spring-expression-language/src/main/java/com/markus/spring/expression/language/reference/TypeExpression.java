package com.markus.spring.expression.language.reference;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 10:25 PM
 * @Description: 类型 相关的 SpEL 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class TypeExpression {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
        System.out.println(dateClass);

        Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(stringClass);

        boolean trueValue = parser.parseExpression(
                        "T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
                .getValue(Boolean.class);
        System.out.println(trueValue);
    }
}
