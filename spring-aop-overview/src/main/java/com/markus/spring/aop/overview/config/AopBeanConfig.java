package com.markus.spring.aop.overview.config;

import com.markus.spring.aop.overview.service.AService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/10
 * @Description:
 */
@Configuration
@EnableAspectJAutoProxy // 开启 AOP 代理
@ComponentScan("com.markus.spring.aop.overview")
public class AopBeanConfig {

  @Bean
  public AService aService() {
    return new AService();
  }
}
