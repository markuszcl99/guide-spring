package com.markus.spring.aop.overview;

import java.util.Random;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public class DefaultEchoService implements EchoService {
  @Override
  public String echo(String message) {
    // 随机故意抛出一个异常
    Random random = new Random(2);
    if (random.nextBoolean()) {
      throw new RuntimeException("For purpose. ");
    }
    return "Default Echo Service echo ( " + message + " )";
  }

  /**
   * JDK 不会被代理
   * CGLIB 会被代理
   * @param message
   * @return
   */
  public String personal(String message) {
    return "Default Echo Service personal ( " + message + " )";
  }
}
