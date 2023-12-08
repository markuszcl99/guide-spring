package com.markus.spring.configuration.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/8
 * @Description:
 */
@Configuration
public class BeanConfig {

  @Bean("user-by-annotation")
  public User user() {
    return User.createUser();
  }
}
