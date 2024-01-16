package com.markus.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description:
 */
public class AspectXmlConfig {

    private void methodPointcut() {
    }

    private Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around invoke method : " + pjp.getSignature());
        return pjp.proceed();
    }

    public void beforeMethod() {
        System.out.println("before invoke method");
    }
}
