package com.markus.spring.data.jdbc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: markus
 * @date: 2024/2/1 11:07 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@Import(JbdcConfig.class)
@PropertySource("classpath:jdbc.properties")
@ComponentScan({
        "com.markus.spring.data.jdbc"
})
public class ApplicationConfiguration {
}
