package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class BeanConfig2 {
  @Bean
  public User user2() {
    User user = new User();
    user.setId(2L);
    user.setUsername("register by BeanConfig2 @Bean");
    return user;
  }

}
