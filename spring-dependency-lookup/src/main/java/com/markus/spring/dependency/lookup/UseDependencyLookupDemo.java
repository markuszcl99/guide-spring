package com.markus.spring.dependency.lookup;

import com.markus.spring.ioc.overview.annotation.Super;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author: markus
 * @date: 2023/12/17 9:32 PM
 * @Description: 依赖查找示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class UseDependencyLookupDemo {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");

        // 1. 实时查找
        realtimeLookup(beanFactory);
        System.out.println("========================");
        // 2. 延迟查找
        lazyLookup(beanFactory);
        System.out.println("========================");
        // 3. 按照类型查找单个Bean
        typeLookup(beanFactory);
        System.out.println("========================");
        // 4. 按照类型查找多个Bean
        collectionLookup(beanFactory);
        System.out.println("========================");
        // 5. 按照注解类型查找集合Bean
        annotationLookup(beanFactory);
    }


    /**
     * ========================按照 Bean 名称查找========================
     */

    /**
     * 实时查找
     */
    private static void realtimeLookup(BeanFactory beanFactory) {
        // 名称+类型
        User user = beanFactory.getBean("user", User.class);
        System.out.println("实时查找: " + user);
    }

    /**
     * 延迟查找
     */
    private static void lazyLookup(BeanFactory beanFactory) throws Exception {
        @SuppressWarnings("unchecked")
        ObjectFactory<User> factoryBean = (ObjectFactory<User>) beanFactory.getBean("factoryBean");
        System.out.println("延迟生效中....");
        System.out.println("延迟查找: " + factoryBean.getObject());
    }

    /**
     * ========================按照 Bean 类型查找========================
     */

    /**
     * 单个Bean类型查找
     *
     * @param beanFactory
     */
    private static void typeLookup(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    /**
     * 根据集合类型查找
     */
    private static void collectionLookup(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            userMap.forEach((beanName, user) -> System.out.println("Bean name: " + beanName + ", User: " + user));
        }
    }

    /**
     * ========================按照注解查找========================
     */
    /**
     * 根据注解查找集合Bean
     *
     * @param beanFactory
     */
    private static void annotationLookup(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            beansWithAnnotation.forEach((beanName, bean) -> System.out.println("Bean name: " + beanName + ", Bean: " + bean));
        }
    }
}
