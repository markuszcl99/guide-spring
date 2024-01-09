package com.markus.spring.annotation.config;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(BeanConfig4.class);
    BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
  }
}
