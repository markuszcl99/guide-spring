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
    Expression exp = parser.parseExpression("'Hello,World!'");
    System.out.println(exp.getValue());
  }
}
