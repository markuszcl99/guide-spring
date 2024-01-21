package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: markus
 * @date: 2024/1/21 9:32 PM
 * @Description: List 集合有关 SpEL 表达式的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ListExpression {
    public static void main(String[] args) {
        Inventor inventor = InventorBuilder.builder();
        List<Inventor> inventors = new ArrayList<>();
        inventors.add(inventor);
        EvaluationContext context = new StandardEvaluationContext(inventors);

        ExpressionParser parser = new SpelExpressionParser();
        // by [index] get element
        Inventor inventorFromParser = parser.parseExpression("[0]").getValue(context, Inventor.class);
        System.out.println(inventorFromParser);

        // inline list
        // 1. simple type
        @SuppressWarnings("unchecked")
        List<Integer> integers = parser.parseExpression("{1,2,3,4,5}").getValue(context, List.class);
        System.out.println(integers);
        // 2. complex type
        @SuppressWarnings("unchecked")
        List<Inventor> inventorList = (List<Inventor>) parser.parseExpression("{T(com.markus.spring.expression.language.InventorBuilder).builder()}").getValue(context);
        System.out.println(inventorList);
    }
}
