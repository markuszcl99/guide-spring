package com.markus.spring.bean.factory;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description:
 */
public class DefaultUserFactory implements ObjectFactory<User> {
  @Override
  public User getObject() throws BeansException {
    return User.createUser();
  }
}
