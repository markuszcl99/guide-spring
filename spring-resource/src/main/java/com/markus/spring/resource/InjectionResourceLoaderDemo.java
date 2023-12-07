package com.markus.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/7
 * @Description: 注入 ResourceLoader
 * @see ResourceLoader
 */
public class InjectionResourceLoaderDemo implements ResourceLoaderAware {
  private ResourceLoader resourceLoader;

  @Autowired
  private ResourceLoader autowiredResourceLoader;

  @Autowired
  private ApplicationContext applicationContext;


  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @PostConstruct
  public void init() {
    System.out.println(resourceLoader);
    System.out.println(resourceLoader == autowiredResourceLoader);
    System.out.println(resourceLoader == applicationContext);
  }


  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(InjectionResourceLoaderDemo.class);
    context.refresh();
    context.close();
  }
}
