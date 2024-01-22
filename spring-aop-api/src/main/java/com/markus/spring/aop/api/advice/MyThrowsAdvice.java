package com.markus.spring.aop.api.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: markus
 * @date: 2024/1/22 10:45 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MyThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.printf("Exception is %s\n", e);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.printf("Method id %s, args is %s, target is %s, exception is %s\n",
                method,
                Arrays.asList(args),
                target,
                e);
    }
}
