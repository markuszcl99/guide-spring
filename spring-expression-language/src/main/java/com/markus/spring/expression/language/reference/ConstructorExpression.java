package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 10:31 PM
 * @Description: 构造器 SpEL 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ConstructorExpression {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Inventor einstein = parser.parseExpression(
                        "new com.markus.spring.expression.language.Inventor()")
                .getValue(Inventor.class);
        System.out.println(einstein);
    }
}
