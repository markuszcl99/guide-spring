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
 * @date: 2024/1/21 11:17 PM
 * @Description: 集合元素选择 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CollectionProjectionExpression {
    public static void main(String[] args) {
        EvaluationContext context = new StandardEvaluationContext();
        List<Inventor> inventors = new ArrayList<>();
        inventors.add(InventorBuilder.builder());
        inventors.add(InventorBuilder.builder("Luna"));
        context.setVariable("inventors", inventors);

        ExpressionParser parser = new SpelExpressionParser();
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) parser.parseExpression(
                "#inventors.![name]").getValue(context);
        list.forEach(ele -> {
            System.out.println(ele + " ");
        });
    }
}
