package com.markus.spring.aop.features.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: markus
 * @date: 2024/1/16 11:14 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class EchoServiceStaticMethodMatcherPointcut extends StaticMethodMatcherPointcut {

    private String methodName;
    private Class<?> targetClass;

    public EchoServiceStaticMethodMatcherPointcut() {
    }

    public EchoServiceStaticMethodMatcherPointcut(String methodName, Class<?> targetClass) {
        this.methodName = methodName;
        this.targetClass = targetClass;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(method.getName(), methodName) && this.targetClass.isAssignableFrom(targetClass);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }
}
