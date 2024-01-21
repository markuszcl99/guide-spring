package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: markus
 * @date: 2024/1/21 10:35 PM
 * @Description: 变量 SpEL 表达式示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class VariableExpression {
    public static void main(String[] args) {
        Inventor inventor = InventorBuilder.builder();
        inventor.getBooleans().add(true);
        inventor.getBooleans().add(false);

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("newName", "Luna");

        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("name = #newName").getValue(context, inventor);
        System.out.println(inventor);

        // #this and #root
        // #this 总是指向当前表达式中计算的对象
        // #root 总是指向根对象

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        context.setVariable("integers", integers);
        String thisExpression = "#integers.?[#this > 3]";
        @SuppressWarnings("unchecked")
        List<Integer> gt3List = parser.parseExpression(thisExpression).getValue(context, List.class);
        gt3List.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        // #root
        context = new StandardEvaluationContext(inventor);
        Inventor value = parser.parseExpression("#root").getValue(context, Inventor.class);
        System.out.println(value);
    }
}
