package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/30
 * @Description: BeanDefinition api使用示例
 */
public class AbstractBeanDefinitionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    beanDefinitionBuilder.addPropertyValue("id", 1L)
        .addPropertyValue("username", "markus zhang");
    AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    beanFactory.registerBeanDefinition("userByBuilder", beanDefinition);

    AbstractBeanDefinition beanDefinitionByManualCreation = new GenericBeanDefinition();
    beanDefinitionByManualCreation.setBeanClass(User.class);
    MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
    mutablePropertyValues.add("id", 2L)
        .add("username", "luna");
    beanDefinitionByManualCreation.setPropertyValues(mutablePropertyValues);
    beanFactory.registerBeanDefinition("userByManualCreation", beanDefinitionByManualCreation);

    Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
    System.out.println(beansOfType);
  }
}
