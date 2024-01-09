package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
public class BeanConfig3 {
  @Bean
  public User user3() {
    User user = new User();
    user.setId(3L);
    user.setUsername("register by BeanConfig3 @Bean");
    return user;
  }

}
