package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.component.UserComponent;
import com.markus.spring.configuration.config.BeanConfig;
import com.markus.spring.ioc.overview.domain.SuperUser;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/13
 * @Description: 利用注解 装载 IoC 容器信息
 */
@Configuration
@Import(BeanConfig.class)
@ImportResource("classpath:/META-INF/annotated-as-ioc-beans.xml") // 代替 xml中的 <import />
@ComponentScans({
    @ComponentScan(basePackages = "com.markus.spring.configuration.component")
})
@PropertySources({
    @PropertySource(value = "classpath:/META-INF/user.properties", encoding = "UTF-8")
})
public class AnnotatedSpringAsIoCContainerDemo {

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
    context.register(AnnotatedSpringAsIoCContainerDemo.class);
    context.refresh();

    UserComponent userComponent = context.getBean(UserComponent.class);
    System.out.println(userComponent);

    Map<String, User> beansOfType = context.getBeansOfType(User.class);
    beansOfType.forEach((beanName, bean) -> {
      System.out.println("Bean name: " + beanName + ", Bean instance: " + bean);
    });

    context.close();
  }
}
