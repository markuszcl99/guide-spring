package com.markus.spring.ioc.overview.domain;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/27
 * @Description:
 */
public class User implements BeanNameAware {
    private Long id;
    private String username;

    private String beanName;

    public User() {
        System.out.println("开始初始化");
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean [" + beanName + "] 初始化完成...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean [" + beanName + "] 销毁中...");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("markus zhang");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
