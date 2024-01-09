package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class BeanConfig4 {
  @Bean
  public User user4() {
    User user = new User();
    user.setId(4L);
    user.setUsername("register by BeanConfig4 @Bean");
    return user;
  }

}
