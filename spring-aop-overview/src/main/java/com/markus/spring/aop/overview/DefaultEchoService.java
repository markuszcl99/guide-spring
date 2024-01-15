package com.markus.spring.aop.overview;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public class DefaultEchoService implements EchoService {
  @Override
  public String echo(String message) {
    // 故意抛出一个异常
//    int i = 1 / 0;
    return "Default Echo Service echo ( " + message + " )";
  }
}
