package com.markus.spring.expression.language;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: markus
 * @date: 2024/1/21 5:08 PM
 * @Description: {@link SpelParserConfiguration} 示例
 * @Blog: https://markuszhang.com
 * @see SpelParserConfiguration
 * It's my honor to share what I've learned with you!
 */
public class SpelParserConfigurationDemo {
    public static void main(String[] args) {

        // 自动生成空对象、自动扩容
        SpelParserConfiguration spelParserConfiguration = new SpelParserConfiguration(true, true);
        // 创建 SpEL 解析器
        SpelExpressionParser parser = new SpelExpressionParser(spelParserConfiguration);

        Expression expression = parser.parseExpression("booleans[3]");

        Inventor inventor = new Inventor();
        Object value = expression.getValue(inventor);
        System.out.println(value);

    }
}
