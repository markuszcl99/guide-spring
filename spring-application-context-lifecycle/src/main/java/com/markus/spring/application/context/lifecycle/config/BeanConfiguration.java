package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.Documented;

/**
 * @author: markus
 * @date: 2024/5/15 12:31 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public EchoService echoService(){
        return new EchoService();
    }

    /**
     * 1. 查找候选 Configuration Class，并设置相应的属性值，如 顺序、轻重量级 Configuration
     * 2. 按照顺序排序
     * 3. 查找自定义命名器，如果有的话
     * 4. 构建 ConfigurationClassParser，用来解析每一个 @Configuration Class Bean
     *  4.1 解析
     *  4.2 校验
     *      4.2.1 【类修饰符校验】检验 @Configuration Class 修饰符不能是 final 的
     *      4.2.2 【方法修饰符校验】校验 @Configuration Class 里面的 @Bean Method 不能是 final、private 的
     */
}
