package com.markus.spring.bean.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Annotation;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/8
 * @Description:
 */
@AnnotationDemo
public class Demo {
  public static void main(String[] args) {
    Import anImport = Demo.class.getAnnotation(Import.class);
    System.out.println(anImport);
  }
}
