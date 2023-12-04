package com.markus.spring.bean.factory;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description:
 */
public class UserFactoryBean implements FactoryBean<User> {
  @Override
  public User getObject() throws Exception {
    return User.createUser();
  }

  @Override
  public Class<?> getObjectType() {
    return User.class;
  }
}
