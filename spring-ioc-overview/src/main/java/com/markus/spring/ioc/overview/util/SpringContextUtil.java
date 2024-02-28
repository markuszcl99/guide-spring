package com.markus.spring.ioc.overview.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/2/9 9:16 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName, Class<T> type) {
        Assert.notNull(applicationContext, "application context must be not null!");
        return applicationContext.getBean(beanName, type);
    }

    public static <T> T getBean(Class<T> type) {
        Assert.notNull(applicationContext, "application context must be not null!");
        return applicationContext.getBean(type);
    }

    public static Object getBean(String beanName) {
        Assert.notNull(applicationContext, "application context must be not null!");
        return applicationContext.getBean(beanName);
    }

}
