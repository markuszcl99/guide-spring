package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.SuperUser;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/14
 * @Description: 外部化配置示例
 */
@PropertySource(value = "classpath:/META-INF/user.properties", encoding = "UTF-8")
//@Configuration
public class PropertyResourceDemo {

  @Bean("user-by-property")
  public User user(@Value("${user.id}") Long userId,
                   @Value("${user.name}") String userName,
                   @Value("${user.address}") String address) {
    SuperUser user = new SuperUser();
    user.setId(userId);
    user.setUsername(userName);
    user.setAddress(address);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(PropertyResourceDemo.class);
    Map<String, Object> propertySourceMap = new HashMap<>();
    propertySourceMap.put("user.name", "张xxx");
    MapPropertySource mapPropertySource = new MapPropertySource("first-property-source", propertySourceMap);
    context.getEnvironment().getPropertySources().addFirst(mapPropertySource);
    // 启动 Spring 应用上下文
    context.refresh();

    User user = context.getBean("user-by-property", User.class);
    System.out.println(user);

    System.out.println(context.getEnvironment().getPropertySources());

    // 题外内容 如果 配置类没有加 @Configuration 注解，则里面的@Bean标注的方法被多次调用时 会产生不同的实例
    PropertyResourceDemo propertyResourceDemo = context.getBean(PropertyResourceDemo.class);
    System.out.println(propertyResourceDemo);
    User userShangHai = propertyResourceDemo.user(1L, "张xxx", "上海");
    User userShangHai2 = propertyResourceDemo.user(1L, "张xxx", "上海");
    System.out.println(userShangHai == userShangHai2);

    // 关闭 Spring 应用上下文
    context.close();
  }
}
