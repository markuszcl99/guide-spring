package com.markus.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: markus
 * @date: 2023/12/24 6:07 PM
 * @Description: 排除被依赖注入的接口示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class IgnoreDependencyDemo implements EnvironmentAware {

    private Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");

        IgnoreDependencyDemo bean = context.getBean(IgnoreDependencyDemo.class);
        System.out.println(bean.environment);

        context.close();
    }
}
