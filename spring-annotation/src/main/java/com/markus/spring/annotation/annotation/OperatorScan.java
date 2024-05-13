package com.markus.spring.annotation.annotation;


import com.markus.spring.annotation.config.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyImportBeanDefinitionRegistrar.class) // org.springframework.context.annotation.ConfigurationClassParser.collectImports
public @interface OperatorScan {
}
