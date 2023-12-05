package com.markus.spring.bean.factory;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description:
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

  @PostConstruct
  public void init() {
    System.out.println("@PostConstruct : DefaultUserFactory初始化中...");
  }

  public void initUserFactory() {
    System.out.println("自定义初始化方法 initUserFactory() : DefaultUserFactory初始化中...");
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean#afterPropertiesSet() : DefaultUserFactory初始化中...");
  }

  @PreDestroy
  public void destroyMethod() {
    System.out.println("@PreDestroy : DefaultUserFactory销毁中...");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("DisposableBean#destroy() : DefaultUserFactory销毁中...");
  }

  public void doDestroy() {
    System.out.println("自定义销毁方法 doDestroy() : DefaultUserFactory销毁中...");
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.println("当前对象[DefaultUserFactory]正在被回收...");
  }
}
