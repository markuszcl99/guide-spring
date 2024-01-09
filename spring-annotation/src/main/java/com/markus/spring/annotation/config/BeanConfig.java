package com.markus.spring.annotation.config;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/9
 * @Description:
 */
@Configuration
@Import({BeanConfig2.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@ImportResource({
    "classpath:/META-INF/application-context.xml"
})
@PropertySource("classpath:/META-INF/user.properties")
public class BeanConfig {

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setUsername("register by BeanConfig @Bean");
    return user;
  }

  @Bean
  public User user6(@Value("${user.id}") Long id, @Value("${user.username}") String username) {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    return user;
  }
}
