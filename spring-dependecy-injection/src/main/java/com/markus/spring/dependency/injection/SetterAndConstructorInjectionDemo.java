package com.markus.spring.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import com.markus.spring.ioc.overview.domain.UserListHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2023/12/24 10:57 AM
 * @Description: setter方法和构造器注入 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SetterAndConstructorInjectionDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");

//        User user = context.getBean("user", User.class);
//        System.out.println(user);
//
//        User userByConstructor = context.getBean("user-by-constructor", User.class);
//        System.out.println(userByConstructor);

        UserListHolder userListHolder = context.getBean("user-list-holder", UserListHolder.class);
        System.out.println(userListHolder);

        context.close();
    }
}
