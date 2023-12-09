package com.markus.spring.annotation.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: markus
 * @date: 2023/12/9 11:49 PM
 * @Description: Cglibe代理示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CglibProxyDemo {
    public static void main(String[] args) {
        // 创建 Enhancer 类
        Enhancer enhancer = new Enhancer();
        // 设置父类，即被代理类
        enhancer.setSuperclass(Person.class);
        // 设置回调，即MethodInterceptor实现类
        enhancer.setCallback(new MyMethodInterceptor());

        // 通过 Enhancer 创建代理对象
        Person personProxy = (Person) enhancer.create(new Class[]{String.class}, new Object[]{"Person"});
        // 调用代理对象的方法
        personProxy.sayHello();
    }

    static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("Before method invocation");
            // 调用被代理方法
            // 切记不要调用 method.invoke()
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("After method invocation");
            return result;
        }
    }
}
