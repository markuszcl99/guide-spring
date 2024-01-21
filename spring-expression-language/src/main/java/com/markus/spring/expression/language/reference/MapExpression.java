package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: markus
 * @date: 2024/1/21 9:47 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MapExpression {
    public static void main(String[] args) {
        Inventor inventor = InventorBuilder.builder();
        Map<String, Inventor> map = new HashMap();
        map.put("markus", inventor);
        EvaluationContext context = new StandardEvaluationContext(map);

        ExpressionParser parser = new SpelExpressionParser();
        // by ['key'] get element
        Inventor inventorFromParser = parser.parseExpression("['markus']").getValue(context, Inventor.class);
        System.out.println(inventorFromParser);

        // nested property access
        String name = parser.parseExpression("['markus'].name").getValue(context, String.class);
        System.out.println(name);

        // inline map
        @SuppressWarnings("unchecked")
        Map<String, Inventor> inventorMap = parser
                .parseExpression("{'markus':T(com.markus.spring.expression.language.InventorBuilder).builder()}")
                .getValue(context, Map.class);
        System.out.println(inventorMap);

    }
}
