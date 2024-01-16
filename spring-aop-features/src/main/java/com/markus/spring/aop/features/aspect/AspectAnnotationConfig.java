package com.markus.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/16
 * @Description:
 */
@Aspect
public class AspectAnnotationConfig {

  @Pointcut("execution(public * *.*(..))")
  private void pointcut() {

  }

  @Before("pointcut()")
  public void before() {
    System.out.println("@Before invoke method");
  }
}
