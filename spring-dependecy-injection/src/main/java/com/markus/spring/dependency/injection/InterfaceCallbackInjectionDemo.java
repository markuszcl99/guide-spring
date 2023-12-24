package com.markus.spring.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2023/12/24 11:06 AM
 * @Description: 接口回调注入 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class InterfaceCallbackInjectionDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");

        User user = context.getBean("user", User.class);
        System.out.println("User Bean Name is [ " + user.getBeanName() + " ]");

        context.close();
    }
}
