package com.markus.spring.aop.features;

import com.markus.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2024/1/17 11:26 PM
 * @Description: 通过 Bean 名称进行自动拦截 生成代理
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanNameAutoProxyCreatorDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-auto-proxy.xml");
        EchoService echoService = context.getBean(EchoService.class);
        System.out.println(echoService.echo("Hello,World"));
        context.close();
    }
}
