package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description: Bean 实例化的几种方式
 */
public class BeanInstantiationDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

    Map<String, User> userMapBeans = context.getBeansOfType(User.class);
    userMapBeans.forEach((k, v) -> System.out.println("bean name : [ " + k + " ]" + ", bean : [ " + v + " ]"));

    context.close();
  }
}
