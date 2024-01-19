package com.markus.spring.expression.language;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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
  }
}
