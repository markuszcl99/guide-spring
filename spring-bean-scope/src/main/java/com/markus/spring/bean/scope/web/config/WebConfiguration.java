package com.markus.spring.bean.scope.web.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: markus
 * @date: 2023/12/17 5:51 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
//@EnableWebMvc
public class WebConfiguration {

    @Bean
//    @RequestScope    // request 作用域
//    @SessionScope  // session 作用域 有互斥锁，会有性能问题
    @ApplicationScope  // Servlet上下文 作用域
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setUsername("markus zhang");
        return user;
    }
}
