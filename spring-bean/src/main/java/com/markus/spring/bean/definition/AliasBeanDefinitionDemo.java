package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/1
 * @Description: 别名注册bean示例
 */
public class AliasBeanDefinitionDemo {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition.xml");
    User user = beanFactory.getBean("user4", User.class);
    System.out.println(user);
  }
}
