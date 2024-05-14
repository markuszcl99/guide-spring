package com.markus.spring.application.context.lifecycle;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author: markus
 * @date: 2024/5/15 12:09 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MyBeanFactoryPostProcessorFactoryBean implements FactoryBean<MyBeanFactoryPostProcessor3> {
    /**
     * FactoryBean 也会被加入进去
     */
    @Override
    public MyBeanFactoryPostProcessor3 getObject() throws Exception {
        return new MyBeanFactoryPostProcessor3();
    }

    @Override
    public Class<?> getObjectType() {
        return MyBeanFactoryPostProcessor3.class;
    }
}
