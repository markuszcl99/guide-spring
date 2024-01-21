package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: markus
 * @date: 2024/1/21 11:17 PM
 * @Description: 集合元素选择 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CollectionSelectionExpression {
    public static void main(String[] args) {
        EvaluationContext context = new StandardEvaluationContext();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        context.setVariable("integers", integers);

        ExpressionParser parser = new SpelExpressionParser();
        @SuppressWarnings("unchecked")
        List<Integer> list = (List<Integer>) parser.parseExpression(
                "#integers.?[#this == 3 || #this == 4]").getValue(context);
        list.forEach(ele -> {
            System.out.print(ele + " ");
        });
    }
}
