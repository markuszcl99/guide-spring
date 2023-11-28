package com.markus.spring.ioc.overview.repository;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collection;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/28
 * @Description: 用户存储
 */
public class UserRepository {
  private User user;

  private Collection<User> users;

  private BeanFactory beanFactory;

  private Environment environment;

  private ApplicationContext applicationContext;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Collection<User> getUsers() {
    return users;
  }

  public void setUsers(Collection<User> users) {
    this.users = users;
  }

  public BeanFactory getBeanFactory() {
    return beanFactory;
  }

  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

}
