package com.markus.spring.aop.overview;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public class DefaultEchoService implements EchoService {
  @Override
  public String echo(String message) {
    return "Default Echo Service echo ( " + message + " )";
  }
}
