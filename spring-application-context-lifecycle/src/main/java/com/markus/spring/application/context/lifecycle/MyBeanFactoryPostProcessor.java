package com.markus.spring.application.context.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author: markus
 * @date: 2024/5/14 12:10 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        MyBeanFactoryPostProcessor2 myBeanFactoryPostProcessor2 = new MyBeanFactoryPostProcessor2();
        // 这里没办法再注册 BeanFactoryPostProcessor 了，
        // 所以 org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.List<org.springframework.beans.factory.config.BeanFactoryPostProcessor>)
        // 不需要再像处理 BeanDefinitionRegistryPostProcessors，处理 BeanFactoryProcessor
    }
}
