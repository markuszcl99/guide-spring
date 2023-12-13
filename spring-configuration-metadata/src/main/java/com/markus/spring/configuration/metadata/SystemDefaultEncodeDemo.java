package com.markus.spring.configuration.metadata;

import java.nio.charset.Charset;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/13
 * @Description:
 */
public class SystemDefaultEncodeDemo {
  public static void main(String[] args) {
    Charset charset = Charset.defaultCharset();
    System.out.println(charset);
  }
}
