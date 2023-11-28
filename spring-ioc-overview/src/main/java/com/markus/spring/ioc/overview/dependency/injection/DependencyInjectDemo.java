package com.markus.spring.ioc.overview.dependency.injection;

import com.markus.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/28
 * @Description: 依赖注入示例代码
 */
public class DependencyInjectDemo {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");
    System.out.println("====================================手动配置完成依赖注入====================================");
    UserRepository userRepository = beanFactory.getBean("userRepositoryManualInjection",UserRepository.class);
    System.out.println(userRepository);

    System.out.println("====================================自动绑定====================================");
    UserRepository userRepositoryByAutowire = beanFactory.getBean("userRepositoryAutowiring",UserRepository.class);
    System.out.println(userRepositoryByAutowire.getEnvironment());
    System.out.println(userRepositoryByAutowire.getBeanFactory());
    System.out.println(userRepositoryByAutowire.getApplicationContext());
  }
}
