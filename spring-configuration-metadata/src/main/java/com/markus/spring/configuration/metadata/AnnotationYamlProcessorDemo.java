package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.factory.YamlPropertySourceFactory;
import com.markus.spring.ioc.overview.domain.SuperUser;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/15
 * @Description:
 */
@PropertySource(
    name = "yaml-config",
    value = "classpath:/META-INF/user.yaml",
    factory = YamlPropertySourceFactory.class)
public class AnnotationYamlProcessorDemo {

  @Bean("user-by-property")
  public User user(@Value("${user.id}") Long userId,
                   @Value("${user.name}") String userName) {
    SuperUser user = new SuperUser();
    user.setId(userId);
    user.setUsername(userName);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AnnotationYamlProcessorDemo.class);
    // 启动 Spring 应用上下文
    context.refresh();

    User user = context.getBean("user-by-property", User.class);
    System.out.println(user);

    // 关闭 Spring 应用上下文
    context.close();
  }
}
