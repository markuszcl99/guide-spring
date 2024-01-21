package com.markus.spring.expression.language.reference;

import com.markus.spring.expression.language.Inventor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 11:07 PM
 * @Description: 三元表达式 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class TernaryOperatorExpression {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        String falseString = parser.parseExpression(
                "false ? 'trueExp' : 'falseExp'").getValue(String.class);
        System.out.println("false ? 'trueExp' : 'falseExp' : " + falseString);

        // The Elvis operator
        String name = parser.parseExpression("name?:'Unknown'").getValue(new Inventor(), String.class);
        System.out.println(name);  // 'Unknown'
    }
}
