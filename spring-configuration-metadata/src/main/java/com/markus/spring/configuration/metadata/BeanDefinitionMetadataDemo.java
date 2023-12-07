package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: markus
 * @date: 2023/12/7 10:17 PM
 * @Description: BeanDefinition 元信息示例
 * @Blog: https://markuszhang.com
 * @see BeanDefinition
 * It's my honor to share what I've learned with you!
 */
public class BeanDefinitionMetadataDemo {
    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // PropertyValue
        beanDefinitionBuilder.addPropertyValue("username", "markus zhang");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // Attribute 附加属性 不会对 Bean 的实例化产生影响（除非定制化）
        beanDefinition.setAttribute("username", "张成龙");
        // beanDefinition 的来源为 BeanDefinitionMetadataDemo
        beanDefinition.setSource(BeanDefinitionMetadataDemo.class);


        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (BeanDefinitionMetadataDemo.class.equals(beanDefinition.getSource())) {
                    if (bean instanceof User) {
                        String username = (String) beanDefinition.getAttribute("username");
                        ((User) bean).setUsername(username);
                    }
                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User bean = beanFactory.getBean(User.class);
        System.out.println(bean);
    }
}
