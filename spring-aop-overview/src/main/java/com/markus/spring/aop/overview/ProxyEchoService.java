package com.markus.spring.aop.overview;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description:
 */
public class ProxyEchoService implements EchoService {

  private final EchoService echoService;

  public ProxyEchoService(EchoService echoService) {
    this.echoService = echoService;
  }


  @Override
  public String echo(String message) {
    long startTime = System.currentTimeMillis();
    String result = echoService.echo(message);
    long endTime = System.currentTimeMillis();
    System.out.println("echo invoke cost [" + (endTime - startTime) + "] ms");
    return result;
  }
}
