package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: markus
 * @date: 2024/5/15 12:31 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class BeanConfiguration2 {

    @Bean("echo1")
    public EchoService echoService(){
        return new EchoService();
    }
}
