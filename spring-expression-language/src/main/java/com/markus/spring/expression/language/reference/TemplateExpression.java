package com.markus.spring.expression.language.reference;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 11:29 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class TemplateExpression {
    public static void main(String[] args) {

        ExpressionParser parser = new SpelExpressionParser();

        String randomPhrase = parser.parseExpression(
                "random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        System.out.println("random number is #{T(java.lang.Math).random()} : " + randomPhrase);
    }

    public static class TemplateParserContext implements ParserContext {

        public String getExpressionPrefix() {
            return "#{";
        }

        public String getExpressionSuffix() {
            return "}";
        }

        public boolean isTemplate() {
            return true;
        }
    }
}
