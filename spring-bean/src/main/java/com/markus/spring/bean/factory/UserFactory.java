package com.markus.spring.bean.factory;

import com.markus.spring.ioc.overview.domain.User;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/5
 * @Description:
 */
public interface UserFactory {

  default User createUser() {
    return User.createUser();
  }
}
