package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: markus
 * @date: 2024/5/15 12:31 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@ConditionalOnMissingBean({BeanConfiguration.class})
@Import(BeanConfiguration3.class)
public class BeanConfiguration2 {

    @Bean("echo1")
    public EchoService echoService(){
        return new EchoService();
    }
}
