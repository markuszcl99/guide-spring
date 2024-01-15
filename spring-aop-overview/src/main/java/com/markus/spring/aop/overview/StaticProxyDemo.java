package com.markus.spring.aop.overview;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/15
 * @Description: 静态代理示例
 */
public class StaticProxyDemo {
  public static void main(String[] args) {
    EchoService echoService = new DefaultEchoService();
    EchoService proxy = new ProxyEchoService(echoService);

    String proxyResult = proxy.echo("Hello,World!");
    System.out.println(proxyResult);
  }
}
