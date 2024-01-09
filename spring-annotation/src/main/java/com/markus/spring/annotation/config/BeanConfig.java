package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
@Configuration
@Import({BeanConfig2.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class BeanConfig {

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setUsername("register by BeanConfig @Bean");
    return user;
  }
}
