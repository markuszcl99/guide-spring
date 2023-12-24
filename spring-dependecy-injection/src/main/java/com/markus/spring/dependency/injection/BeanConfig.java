package com.markus.spring.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: markus
 * @date: 2023/12/24 9:25 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class BeanConfig {

    @Bean
    public User userByConfig() {
        User user = new User();
        user.setId(2L);
        user.setUsername("markus zhang from bean config");
        return user;
    }
}
