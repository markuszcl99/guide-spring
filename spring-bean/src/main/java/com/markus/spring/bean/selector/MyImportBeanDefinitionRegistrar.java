package com.markus.spring.bean.selector;

import com.markus.spring.bean.definition.AnnotationBeanDefinitionDemo;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/1
 * @Description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    AbstractBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClass(AnnotationBeanDefinitionDemo.ConfigB.class);
    registry.registerBeanDefinition("configB", beanDefinition);
  }
}
