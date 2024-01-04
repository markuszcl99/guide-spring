package com.markus.spring.generic;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/4
 * @Description: 研究一下 Spring 使用 {@link ResolvableType} 的场景
 * @see ResolvableType
 * @see DefaultListableBeanFactory#doResolveDependency(DependencyDescriptor, String, Set, TypeConverter)
 */
@Configuration
public class SpringUseResolvableTypeDemo {

  /**
   * 处理依赖注入时使用 ResolvableType 去解析 users 字段的 泛型参数类型 User.class
   * 并通过 User.class 进行依赖查找 + resolvableDependencies 中符合条件的 依赖对象，
   * 最终将该对象注入到 该字段中
   */
  @Autowired
  private List<User> users;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(SpringUseResolvableTypeDemo.class);
    context.refresh();

    SpringUseResolvableTypeDemo bean = context.getBean(SpringUseResolvableTypeDemo.class);
    System.out.println(bean.users);

    context.close();
  }

  @Bean
  public User user1() {
    return User.createUser();
  }

  @Bean
  public User user2() {
    User user = new User();
    user.setId(2L);
    user.setUsername("luna");
    return user;
  }
}
