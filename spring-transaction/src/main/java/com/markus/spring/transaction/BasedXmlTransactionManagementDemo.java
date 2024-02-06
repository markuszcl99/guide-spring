package com.markus.spring.transaction;

import com.markus.spring.transaction.service.BaseXmlUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2024/2/4 10:44 PM
 * @Description: 基于 XML 的事务管理示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BasedXmlTransactionManagementDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-tx-context.xml");
        BaseXmlUserService bean = context.getBean(BaseXmlUserService.class);
        bean.processUser();
    }
}
