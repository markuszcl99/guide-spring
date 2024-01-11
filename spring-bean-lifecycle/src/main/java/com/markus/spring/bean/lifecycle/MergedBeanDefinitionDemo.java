package com.markus.spring.bean.lifecycle;

import com.markus.spring.ioc.overview.domain.SuperUser;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/11
 * @Description: 确认一下 BeanDefinition 合并的结论
 */
public class MergedBeanDefinitionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    reader.loadBeanDefinitions("classpath:/META-INF/merged-bean-definition.xml");

    SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
    System.out.println(superUser);
  }
}
