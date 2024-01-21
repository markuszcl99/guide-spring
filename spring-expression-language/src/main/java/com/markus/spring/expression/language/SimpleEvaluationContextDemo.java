package com.markus.spring.expression.language;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;

/**
 * @author: markus
 * @date: 2024/1/21 3:49 PM
 * @Description: {@link SimpleEvaluationContext } 示例
 * @Blog: https://markuszhang.com
 * @see SimpleEvaluationContext
 * It's my honor to share what I've learned with you!
 */
public class SimpleEvaluationContextDemo {
    public static void main(String[] args) {
        Inventor inventor = new Inventor();
        inventor.setBooleans(new ArrayList<>());
        inventor.getBooleans().add(true);

        EvaluationContext evaluationContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("booleans[0]").setValue(evaluationContext, inventor, "false");
        Boolean b = inventor.getBooleans().get(0);
        System.out.println(b);

        // throw SpelEvaluationException, because of forReadOnlyDataBinding.
        // we can use forReadWriteDataBinding to build EvaluationContext
        parser.parseExpression("booleans").setValue(evaluationContext, inventor, new ArrayList<>());


    }
}
