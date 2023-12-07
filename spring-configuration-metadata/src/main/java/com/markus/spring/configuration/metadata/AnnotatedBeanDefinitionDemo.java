package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author: markus
 * @date: 2023/12/7 10:57 PM
 * @Description: 注解 BeanDefinition 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class AnnotatedBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedBeanDefinitionDemo.class);

        context.refresh();

        BeanDefinition user = context.getBeanDefinition("user");
        User bean = context.getBean(User.class);

        context.close();
    }

    @Bean("user")
    public User user() {
        return User.createUser();
    }
}
