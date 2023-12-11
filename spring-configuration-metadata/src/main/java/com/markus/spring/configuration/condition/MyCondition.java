package com.markus.spring.configuration.condition;

import com.markus.spring.configuration.annotation.Load2IoC;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/11
 * @Description:
 */
public class MyCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    MergedAnnotations annotations = metadata.getAnnotations();
    if (annotations.isPresent(Load2IoC.class)) {
      MergedAnnotation<Load2IoC> load2IoCMergedAnnotation = annotations.get(Load2IoC.class);
      return load2IoCMergedAnnotation.getValue("value", Boolean.class).orElse(true);
    }
    return false;
  }
}
