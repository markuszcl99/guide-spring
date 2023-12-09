package com.markus.spring.annotation;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author: markus
 * @date: 2023/12/9 11:08 PM
 * @Description: @Bean 注解实现原理
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * @see ConfigurationClassPostProcessor
 * // ConfigurationClassParser#doProcessConfigurationClass
 */
@Configuration
public class AtConfigurationClassEnhancerDemo implements BeanFactoryAware, ResourceLoaderAware {

    private BeanFactory beanFactory;

    private ResourceLoader resourceLoader;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 被代理原因: 保证 @Bean 方法得到正确的调用
     * 代理类中 BeanFactoryAwareMethodInterceptor 的拦截作用是，为了在 拦截方法中使用 BeanFactory
     */

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AtConfigurationClassEnhancerDemo.class);

        context.refresh();

        AtConfigurationClassEnhancerDemo atConfigurationClassEnhancerDemo = context.getBean(AtConfigurationClassEnhancerDemo.class);
        User user = context.getBean(User.class);


//        System.out.println("AtConfigurationClassEnhancerDemo#user == user : " + (atConfigurationClassEnhancerDemo.user() == user));
        // 如果不被代理的话，下面会是false
        System.out.println("AtConfigurationClassEnhancerDemo#user == AtConfigurationClassEnhancerDemo#user : " + (atConfigurationClassEnhancerDemo.user() == atConfigurationClassEnhancerDemo.user()));

        context.close();
    }

    @Bean
    public User user() {
        return User.createUser();
    }
}
