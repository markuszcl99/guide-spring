package com.markus.spring.transaction;

import java.util.List;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/4
 * @Description:
 */
public class Test {
  public static void main(String[] args) {
    A a = new A();
    a.bList = null;

    for (B b : a.bList) {
      System.out.println(b);
    }
  }

  static class A {
    List<B> bList;
  }

  static class B {

  }
}
