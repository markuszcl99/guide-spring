package com.markus.spring.configuration.config;

import com.markus.spring.configuration.annotation.Load2IoC;
import com.markus.spring.configuration.condition.MyCondition;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/8
 * @Description:
 */
@Configuration
@Conditional(MyCondition.class)
//@Load2IoC(value = false) // 不会被加载进 IoC 容器
@Load2IoC // 会被加载进 IoC 容器
public class BeanConfig {

  @Bean("user-by-annotation")
  public User user() {
    return User.createUser();
  }
}
