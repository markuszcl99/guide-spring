package com.markus.spring.expression.language;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.GregorianCalendar;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/19
 * @Description: SpEL 解析器示例
 * @see SpelExpressionParser
 */
public class ExpressionParserDemo {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        // 特性一 : 计算 字面量
        Expression exp = parser.parseExpression("'Hello,World'");
        System.out.println(exp.getValue());

        // 特性二 : 方法调用
        exp = parser.parseExpression("'Hello,World'.concat('!')");
        System.out.println(exp.getValue());
        //         调用 getBytes()
        exp = parser.parseExpression("'Hello,World'.bytes");
        System.out.println(exp.getValue());
        //         嵌套调用 getBytes().length
        exp = parser.parseExpression("'Hello,World'.bytes.length");
        System.out.println(exp.getValue());
        //         构造器调用 new String(xxx)
        exp = parser.parseExpression("new String('Hello,World').toUpperCase()");
        System.out.println(exp.getValue());
        //         支持泛型获取 public <T> T getValue(Class<T> desiredResultType)
        System.out.println(exp.getValue(String.class));

        // 特性三 : 针对特定对象实例进行计算
        GregorianCalendar c = new GregorianCalendar();
        c.set(1999, 1, 29);
        Inventor markus = new Inventor("markus zhang", c.getTime(), "China");
        exp = parser.parseExpression("name");
        System.out.println(exp.getValue(markus));
        exp = parser.parseExpression("birthday");
        System.out.println(exp.getValue(markus));
        exp = parser.parseExpression("nationality");
        System.out.println(exp.getValue(markus));
    }
}
