package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.InventorBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author: markus
 * @date: 2024/1/21 10:54 PM
 * @Description: 注册方法 并在 SpEL 中使用 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class FunctionExpression {

    // must be static method
    public static void display(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = FunctionExpression.class.getDeclaredMethod("display", Object.class);

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("display", method);
        context.setVariable("inventor", InventorBuilder.builder());

        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("#display(#inventor)").getValue(context);
    }
}
