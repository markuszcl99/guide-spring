package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.context.annotation.*;

/**
 * @author: markus
 * @date: 2024/5/15 12:31 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
//@ConditionalOnMissingBean({BeanConfiguration.class})
public class BeanConfiguration3 {

    @Bean("proxyEchoService")
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public EchoService proxyEchoService(){
        return new EchoService();
    }

    @Bean("noProxyEchoService")
    @Scope(proxyMode = ScopedProxyMode.NO)
    public EchoService noProxyEchoService(){
        return new EchoService();
    }

    @Bean("static-echo")
    public static EchoService staticEcho(){
        return new EchoService();
    }
}
