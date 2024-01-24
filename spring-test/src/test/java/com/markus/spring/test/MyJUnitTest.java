package com.markus.spring.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/24
 * @Description:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyJUnitTest {
  /**
   * API 简介
   * <p>JUnitCore 单元测试核心 API</p>
   * <p>Request 是要运行测试的抽象描述，支持测试的过滤和排序</p>
   * <p>Runner 运行测试用例和通知 RunNotifier 一些重要的事件，如果自定义 Runner ，需要继承此类</p>
   * <p>RunListener 针对测试用例执行期间收到的事件通知，执行对应逻辑，里面的方法都是空实现，具体的子类可以重写一个或多个方法来执行对应的逻辑</p>
   * <p></p>
   * @param args
   */
  public static void main(String[] args) {
    // 1. 创建 JUnitCore 的实例
    JUnitCore runner = new JUnitCore();
    // 2. 通过单测的 class 对象构建 Request
    Request request = Request.aClass(MyJUnitTest.class);
    // 3. 运行单元测试
    Result result = runner.run(request.getRunner());
    // 4. 打印结果
    System.out.println(JSONObject.toJSON(result));
  }

  @Test
  public void test1() {
    System.out.println("test1");
  }

  @Test
  public void test2() {
    System.out.println("test2");
  }
}
