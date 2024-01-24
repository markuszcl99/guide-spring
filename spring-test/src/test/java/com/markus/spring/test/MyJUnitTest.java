package com.markus.spring.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.*;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.FrameworkMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.Statement;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/24
 * @Description:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyJUnitTest {
  /**
   * 单元测试 API 简介
   * <p>1. {@link JUnitCore} 单元测试核心 API</p>
   * <p>2. {@link Request} 是要运行测试的抽象描述，支持测试的过滤和排序</p>
   * <p>3. {@link Runner} 运行测试用例和通知 {@link RunListener} 一些重要的事件，如果自定义 Runner ，需要继承此类</p>
   * <p>4. {@link RunListener} 针对测试用例执行期间收到的事件通知，执行对应逻辑，里面的方法都是空实现，具体的子类可以重写一个或多个方法来执行对应的逻辑</p>
   * <p>5. {@link EachTestNotifier} 在每个测试方法执行前后发通知</p>
   * <p>6. {@link Description} 测试树 Test Class -> Test Method</p>
   * <p>7. {@link Statement} 表示运行测试套件的时候，要做的一项或多项操作</p>
   * <p>8. {@link FrameworkMethod} 对单元测试方法的抽象</p>
   *
   * <p></p>
   * 单元测试 Annotation 简介
   * <p>1. {@link BeforeClass} 单测套件执行前</p>
   * <p>1. {@link AfterClass}</p>
   *
   * <p></p>
   * 单元测试方法被调用原理: 反射
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
