package com.markus.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: markus
 * @date: 2023/12/17 10:23 PM
 * @Description: {@link HierarchicalBeanFactory}
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class HierarchicalBeanFactoryDependencyDemo {
    public static void main(String[] args) {
        ConfigurableListableBeanFactory subBeanFactory = new DefaultListableBeanFactory();
        // 设置父容器
        subBeanFactory.setParentBeanFactory(createParent());

        // 展示 仅在当前 Bean 容器中是否 存在
        System.out.println(displayContainBean(subBeanFactory, "user", true));
        // 展示 父子 Bean 容器中是否 存在（体现出 可继承 BeanFactory 的示例 即 HierarchicalBeanFactory）
        System.out.println(displayContainBean(subBeanFactory, "user", false));

    }

    private static boolean displayContainBean(ConfigurableListableBeanFactory beanFactory, String beanName, boolean onlyLocal) {
        boolean result = beanFactory.containsLocalBean(beanName);
        if (!onlyLocal) {
            if (!result) {
                BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
                if (parentBeanFactory != null) {
                    result = parentBeanFactory.containsBean(beanName);
                }
            }
        }
        return result;
    }

    private static ConfigurableListableBeanFactory createParent() {
        ConfigurableListableBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) parentBeanFactory);

        String location = "classpath:/META-INF/dependency-lookup.xml";
        // 加载 父容器 的 Bean 实例
        beanDefinitionReader.loadBeanDefinitions(location);
        return parentBeanFactory;
    }
}
