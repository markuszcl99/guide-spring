package com.markus.spring.aop.overview.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/10
 * @Description:
 */
@Aspect // 表示这是一个切面
@Component // 注册到 容器中
public class ServiceAdvice {

//  @Pointcut("execution(public * *.sayHello(..))")
  @Pointcut("target(com.markus.spring.aop.overview.service.AService)")
  public void pointcut() {

  }

  @Before("pointcut()")
  public void before() {
    System.out.println("AOP before ");
  }

}
