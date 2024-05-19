package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.context.annotation.Bean;

/**
 * @author: markus
 * @date: 2024/5/19 6:18 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface IBeanConfiguration {

    @Bean
    default EchoService echoService0(){
        return new EchoService();
    }
}
