package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;

/**
 * @author: markus
 * @date: 2024/1/21 9:10 PM
 * @Description: 数组相关的表达式示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ArraysExpression {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // array
        Inventor[] inventors = {InventorBuilder.builder()};
        EvaluationContext context = new StandardEvaluationContext(inventors);

        // index 表达
        Inventor inventor = parser.parseExpression("[0]").getValue(context, Inventor.class);
        System.out.println(inventor);

        // index + nested property
        String name = parser.parseExpression("[0].name").getValue(context, String.class);
        System.out.println(name);

        // index + nested property index
        inventor.getBooleans().add(true);
        boolean bool = parser.parseExpression("[0].booleans[0]").getValue(context, boolean.class);
        System.out.println(bool);

        // array construction
        int[] numbers = parser.parseExpression("new int[]{1,2,3}").getValue(context, int[].class);
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();

        // complex type arrays constructor
        Inventor[] complexTypeArrays = parser
                .parseExpression("new com.markus.spring.expression.language.Inventor[1]")
                .getValue(context, Inventor[].class);
        complexTypeArrays[0] = InventorBuilder.builder();
        Arrays.stream(complexTypeArrays).forEach(System.out::println);
    }
}
