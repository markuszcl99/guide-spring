package com.markus.spring.ioc.overview.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import com.markus.spring.ioc.overview.domain.UserHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/28
 * @Description: 基于@Autowired注解实现依赖注入的示例
 */
@Configuration // 可用可不用，如果采用该注解，则该Bean会cglib提升
public class AnnotationDependencyInjectByAutowiredDemo {

  @Autowired
  private User user;

  @Autowired
  private BeanFactory beanFactory;

  @Autowired
  private Collection<User> users;

  private User userByMethod;

  @Autowired
  public void setUser(User user) {
    this.userByMethod = user;
  }

  @Autowired
  private UserHolder userHolder;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册组件类
    context.register(AnnotationDependencyInjectByAutowiredDemo.class);
    // 启动上下文
    context.refresh();
    AnnotationDependencyInjectByAutowiredDemo demo = context.getBean(AnnotationDependencyInjectByAutowiredDemo.class);
    // 如果AnnotationDependencyInjectByAutowiredDemo被@Configuration注解标注，则该段输出对象是经过cglib提升后的代理类
    System.out.println("demo = " + demo);
    System.out.println("demo.user = " + demo.user);
    System.out.println("demo.beanFactory = " + demo.beanFactory);
    System.out.println("demo.users = " + demo.users);
    System.out.println("demo.userByMethod = " + demo.userByMethod);
    System.out.println("demo.userHolder = " + demo.userHolder);
  }

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setUsername("markuszhang");
    return user;
  }

  @Bean
  public UserHolder userHolder(@Autowired User user) {
    // 通过@Autowired 将user 注入进来，并在此设置到userHolder中
    UserHolder userHolder = new UserHolder();
    userHolder.setUser(user);
    return userHolder;
  }
}
