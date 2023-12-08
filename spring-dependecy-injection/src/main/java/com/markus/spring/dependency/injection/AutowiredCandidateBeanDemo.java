package com.markus.spring.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import com.markus.spring.ioc.overview.domain.UserListHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author: markus
 * @date: 2023/12/8 10:46 PM
 * @Description: Bean是否作为注入候选 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * @see BeanDefinition
 */
public class AutowiredCandidateBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");

        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        System.out.println("ioc 容器中的 User 类型实例: " + beansOfType);

        UserListHolder userListHolder = context.getBean(UserListHolder.class);
        System.out.println("UserListHolder 中被注入的 User 实例: " + userListHolder);

        // 关闭容器
        context.close();
    }
}
