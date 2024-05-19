package com.markus.spring.application.context.lifecycle.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author: markus
 * @date: 2024/5/19 10:23 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Component
@Aspect
public class EchoServiceAspect {

    @Pointcut(value = "execution(public * com.markus.spring.application.context.lifecycle.service.EchoService.echo(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        System.out.println("target method : [ " + methodSignature.getMethod().getName() + " ] was proxied!");
        return pjp.proceed();
    }

}
