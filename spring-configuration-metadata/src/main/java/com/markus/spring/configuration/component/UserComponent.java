package com.markus.spring.configuration.component;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/13
 * @Description:
 */
@Component
public class UserComponent {
  @Autowired
  private User user;

  @Override
  public String toString() {
    return "UserComponent{" +
        "user=" + user +
        '}';
  }
}
