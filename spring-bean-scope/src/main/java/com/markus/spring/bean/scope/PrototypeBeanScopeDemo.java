package com.markus.spring.bean.scope;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @author: markus
 * @date: 2023/12/16 10:59 AM
 * @Description: 原型 Bean 相关知识点结论验证 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class PrototypeBeanScopeDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User propertyUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User propertyUser2;

    @Autowired
    private Map<String, User> userMap;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PrototypeBeanScopeDemo.class);

        // 1. 依赖查找
        //   1.1 单例
        User singletonUser1 = context.getBean("user", User.class);
        User singletonUser2 = context.getBean("user", User.class);

        System.out.println("singleton1 == singleton2 ? " + (singletonUser1 == singletonUser2));
        System.out.println(singletonUser1);

        //   1.2 原型
        User prototypeUser = context.getBean("prototypeUser", User.class);
        System.out.println(prototypeUser);

        User prototypeUser1 = context.getBean("prototypeUser", User.class);
        System.out.println(prototypeUser1);

        System.out.println();


        // 2. 依赖注入
        PrototypeBeanScopeDemo demo = context.getBean(PrototypeBeanScopeDemo.class);
        System.out.println("demo.user : " + demo.user);
        System.out.println("demo.prototypeUser : " + demo.prototypeUser);
        System.out.println("demo.propertyUser1 : " + demo.propertyUser1);
        System.out.println("demo.propertyUser2 : " + demo.propertyUser2);
        System.out.println("demo.userMap : " + demo.userMap);
    }

    @Bean
    public User user() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        user.setUsername("markus zhang");
        return user;
    }
}
