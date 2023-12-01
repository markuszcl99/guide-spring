package com.markus.spring.bean.selector;

import com.markus.spring.bean.definition.AnnotationBeanDefinitionDemo;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/1
 * @Description:
 */
public class MyImportSelector implements ImportSelector {
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{
        "com.markus.spring.bean.definition.AnnotationBeanDefinitionDemo.ConfigA"
    };
  }
}
