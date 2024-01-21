package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author: markus
 * @date: 2024/1/21 8:59 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class PropertiesExpression {
    public static void main(String[] args) {
        Inventor inventor = InventorBuilder.builder();

        EvaluationContext context = new StandardEvaluationContext(inventor);

        ExpressionParser parser = new SpelExpressionParser();
        String name = parser.parseExpression("name").getValue(context, String.class);
        System.out.println(name);

        // nested property
        int length = parser.parseExpression("name.length").getValue(context, int.class);
        System.out.println(length);
    }
}
