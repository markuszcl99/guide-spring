package com.markus.spring.bean.scope;

import com.markus.spring.ioc.overview.domain.SuperUser;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2023/12/16 11:00 AM
 * @Description: 单例 Bean 相关知识点结论验证 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SingletonBeanScopeDemo {

    /**
     * 单例 Bean 知识点总结:
     * <p>
     * 1. singleton bean 在一个应用中是不唯一的，仅在当前容器中唯一
     * 2. 涉及 父子容器 时，面临同名 Bean 时 不会发生冲突，本身也不在一个容器中
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext parentContext = new ClassPathXmlApplicationContext("classpath:/META-INF/parent-context.xml");
        ClassPathXmlApplicationContext sonContext = new ClassPathXmlApplicationContext("classpath:/META-INF/son-context.xml");

        // 建立 父子容器关系
        sonContext.setParent(parentContext);
        ConfigurableListableBeanFactory beanFactory = sonContext.getBeanFactory();
        ConfigurableListableBeanFactory parentBeanFactory = parentContext.getBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);

        User sonUser = sonContext.getBean("user", User.class);
        System.out.println(sonUser);
        User parentUser = parentContext.getBean("user", User.class);
        System.out.println(parentUser);

        SuperUser superUser = sonContext.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

    }
}
