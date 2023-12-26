package com.markus.spring.dependency.injection.bug;

import com.markus.spring.ioc.overview.domain.User;
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
public class BeanConfigB {

    //  @Autowired
    private Collection<UserEntity> userEntities;

    public BeanConfigB(Collection<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }


    @Bean
    public UserEntity userEntityB1() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("User B1");
        return userEntity;
    }

    @Bean
    public UserEntity userEntityB2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("User B2");
        return userEntity;
    }

    public Collection<UserEntity> getUserEntities() {
        return userEntities;
    }
}
