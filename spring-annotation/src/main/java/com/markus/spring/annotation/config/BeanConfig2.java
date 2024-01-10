package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
@Configuration // 字节码提升 生成代理，防止当前 配置类里面的 @Bean 方法被调用时产生新的实例
public class BeanConfig2 {
  @Bean
  public User user2() {
    User user = new User();
    user.setId(2L);
    user.setUsername("register by BeanConfig2 @Bean");
    return user;
  }

}
