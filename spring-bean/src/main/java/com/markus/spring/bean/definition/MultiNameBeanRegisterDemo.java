package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/30
 * @Description:
 */
public class MultiNameBeanRegisterDemo {
  /**
   * 结论:
   * <p>
   * 1. 当xml中一个Bean被指定为多个name且没有指定id的情况下，会默认指定name的第一个名称做为id，其余作为别名
   * <p>
   * 2. 当有指定id的情况，那么name中指定的名称将全部作为别名
   */
  public static void main(String[] args) {
    ListableBeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition.xml");
    String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }

    String[] aliases = beanFactory.getAliases("user");
    for (String alias : aliases) {
      System.out.println(alias);
    }

    User user2 = beanFactory.getBean("user2", User.class);
    System.out.println(user2);
  }
}
