package com.markus.spring.dependency.injection.bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description:
 */
@Configuration
public class BeanConfigA {

//  @Autowired
  private Collection<UserEntity> userEntities;

  public BeanConfigA(Collection<UserEntity> userEntities) {
    this.userEntities = userEntities;
  }

  @Bean
  public UserEntity userEntityA1() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername("User A1");
    return userEntity;
  }

  @Bean
  public UserEntity userEntityA2() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername("User A2");
    return userEntity;
  }

  public Collection<UserEntity> getUserEntities() {
    return userEntities;
  }
}
