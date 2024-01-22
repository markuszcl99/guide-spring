package com.markus.spring.aop.api.proxyfactory;

import com.markus.spring.aop.api.advice.MyThrowsAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: markus
 * @date: 2024/1/22 10:49 PM
 * @Description: 利用 ProxyFactory 产生代理对象，并向其中添加 Advice 的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ProxyFactoryUseAdviceDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        ProxyFactory proxyFactory = new ProxyFactory(map);

        proxyFactory.addAdvice(new MyThrowsAdvice());
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                throw new RuntimeException("123");
            }
        });

        Map<String, String> proxy = (Map<String, String>) proxyFactory.getProxy();
        proxy.put("A", "A");
    }
}
