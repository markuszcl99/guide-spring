package com.markus.spring.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class MyImportSelector implements ImportSelector {
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{"com.markus.spring.annotation.config.BeanConfig3"};
  }
}
