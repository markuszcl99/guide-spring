package com.markus.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

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
  public void a() throws Throwable {
    System.out.println("@Before invoke a method");
  }

  @Before("pointcut()")
  public void b() throws Throwable {
    System.out.println("@Before invoke b method");
  }

  @Before("pointcut()")
  public void c() throws Throwable {
    System.out.println("@Before invoke c method");
  }

  @Before("pointcut()")
  public void before() throws Throwable {
    System.out.println("@Before invoke before method");
  }

  @Before("pointcut()")
  public void before2() throws Throwable {
    System.out.println("@Before invoke before2 method");
  }

  @After("pointcut()")
  public void after() {
    System.out.println("@After invoke method");
  }

  @AfterThrowing("pointcut()")
  public void afterThrowing() {
    System.out.println("@AfterThrowing invoke method");
  }

  @AfterReturning("pointcut()")
  public void afterReturning() {
    System.out.println("@AfterReturning invoke method");
  }

}
