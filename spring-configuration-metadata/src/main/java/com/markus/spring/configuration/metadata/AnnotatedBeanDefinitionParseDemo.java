package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.config.BeanConfig;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/11
 * @Description:
 */
public class AnnotatedBeanDefinitionParseDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

    beanDefinitionReader.register(BeanConfig.class);

    if (beanFactory.containsBean("beanConfig")) {
      BeanConfig bean = beanFactory.getBean(BeanConfig.class);
      System.out.println(bean);
    }

    System.out.println("BeanFactory 中的 Bean 实例 : ");
    String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
    for (String beanName : beanDefinitionNames) {
      System.out.println(beanName);
    }
  }
}
