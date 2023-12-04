package com.markus.spring.bean.definition;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description: 特殊的Bean 实例化方式
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) throws Exception {
        // jdk 提供的实现
        demoServiceLoader();

        // spring 提供的实现
        demoServiceLoaderFactoryBean();
    }

    private static void demoServiceLoaderFactoryBean() throws Exception {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        ServiceLoader<ObjectFactory> serviceLoader =  beanFactory.getBean("serviceLoaderFactoryBean", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
    }

    private static void demoServiceLoader() {
        ServiceLoader<ObjectFactory> objectFactoryServiceLoader = ServiceLoader.load(ObjectFactory.class);
        displayServiceLoader(objectFactoryServiceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<ObjectFactory> serviceLoader) {
        Iterator<ObjectFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            ObjectFactory objectFactory = iterator.next();
            System.out.println(objectFactory.getObject());
        }
    }
}
