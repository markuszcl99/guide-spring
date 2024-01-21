package com.markus.spring.expression.language.reference;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 10:00 PM
 * @Description: 运算符 SpEL 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class OperatorsExpression {
    /**
     * more demo please reference https://docs.spring.io/spring-framework/reference/core/expressions/language-ref/operators.html
     *
     * @param args
     */
    public static void main(String[] args) {

        ExpressionParser parser = new SpelExpressionParser();
        // first : relational operators
        boolean result = parser.parseExpression("2 < 5").getValue(Boolean.class);
        System.out.println("2 < 5 : " + result);

        result = parser.parseExpression("2 == 5").getValue(Boolean.class);
        System.out.println("2 == 5 : " + result);

        result = parser.parseExpression("1 instanceof T(int)").getValue(Boolean.class);
        System.out.println("1 instanceof T(int) : " + result);
        result = parser.parseExpression("1 instanceof T(Integer)").getValue(Boolean.class);
        System.out.println("1 instanceof T(Integer) : " + result);

        // second : logical operators
        // and (&&)
        // or (||)
        // not (!)
        result = parser.parseExpression("true or false").getValue(Boolean.class);
        System.out.println("true or false : " + result);
        result = parser.parseExpression("true and false").getValue(Boolean.class);
        System.out.println("true and false : " + result);
        result = parser.parseExpression("!true").getValue(Boolean.class);
        System.out.println("!true : " + result);

        // third : mathematical operators
        // + - * / %
        // Addition
        int two = parser.parseExpression("1 + 1").getValue(Integer.class);  // 2
        System.out.println("1 + 1 : " + two);
        String testString = parser.parseExpression(
                "'test' + ' ' + 'string'").getValue(String.class);  // 'test string'
        System.out.println("'test' + ' ' + 'string' : " + testString);
        // Subtraction
        int four = parser.parseExpression("1 - -3").getValue(Integer.class);  // 4
        System.out.println("1 - -3 : " + four);
        double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class);  // -9000
        System.out.println("1000.00 - 1e4 : " + d);
        // Multiplication
        int six = parser.parseExpression("-2 * -3").getValue(Integer.class);  // 6
        System.out.println("-2 * -3 : " + six);
        double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class);  // 24.0
        System.out.println("2.0 * 3e0 * 4 : " + twentyFour);
        // Division
        int minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class);  // -2
        System.out.println("6 / -3 : " + minusTwo);
        double oneD = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class);  // 1.0
        System.out.println("8.0 / 4e0 / 2 : " + oneD);
        // Modulus
        int three = parser.parseExpression("7 % 4").getValue(Integer.class);  // 3
        System.out.println("7 % 4 : " + three);
        int one = parser.parseExpression("8 / 5 % 2").getValue(Integer.class);  // 1
        System.out.println("8 / 5 % 2 : " + one);
        // Operator precedence
        int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class);  // -21
        System.out.println("1+2-3*8 : " + minusTwentyOne);
    }
}
