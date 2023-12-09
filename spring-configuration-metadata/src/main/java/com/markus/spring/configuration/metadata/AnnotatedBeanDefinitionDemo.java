package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: markus
 * @date: 2023/12/7 10:57 PM
 * @Description: 注解 BeanDefinition 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration("annotatedBeanDefinitionDemo")
public class AnnotatedBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedBeanDefinitionDemo.class);

        context.refresh();

        BeanDefinition user = context.getBeanDefinition("user");
        System.out.println(user.getResolvableType());
        System.out.println(user.getResourceDescription());
        User bean = context.getBean(User.class);

        BeanDefinition annotatedBeanDefinitionDemo = context.getBeanDefinition("annotatedBeanDefinitionDemo");
        System.out.println(annotatedBeanDefinitionDemo);
        // 没什么用
        System.out.println(annotatedBeanDefinitionDemo.getOriginatingBeanDefinition());

        context.close();
    }

    @Bean("user")
    public User user() {
        return User.createUser();
    }
}
