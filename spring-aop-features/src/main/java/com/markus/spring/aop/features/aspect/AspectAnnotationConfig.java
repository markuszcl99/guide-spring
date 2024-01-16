package com.markus.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description:
 */
@Aspect
@Order(1)
@Component
public class AspectAnnotationConfig {

    @Pointcut("execution(public * com.markus.spring.aop.overview.EchoService.*(..))")
    private void pointcut() {

    }

    @Around("pointcut()")
    private Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around invoke method : " + pjp.getSignature());
        return pjp.proceed();
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("@Before invoke method");
    }
}
