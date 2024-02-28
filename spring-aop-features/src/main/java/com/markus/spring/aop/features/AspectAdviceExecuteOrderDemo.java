package com.markus.spring.aop.features;

import com.markus.spring.aop.features.aspect.AspectAnnotationConfig;
import com.markus.spring.aop.features.aspect.AspectAnnotationConfig2;
import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.springframework.context.annotation.*;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/28
 * @Description: 切面执行顺序 验证
 */
@Import({
    AspectAnnotationConfig.class,
    AspectAnnotationConfig2.class,
})
@Configuration
@EnableAspectJAutoProxy
public class AspectAdviceExecuteOrderDemo {
  /**
   * 同一切面内 同一类型的通知方式执行顺序是不明确的
   *    1. org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory#getAdvisorMethods(java.lang.Class)
   *    2. java.lang.Class#getDeclaredMethods() 方法注释里有明确标注
   * 同一切面内 不同类型的通知方式执行顺序如下：
   *    1. @Around 方法（非目标方法，是 AOP 包装后的代理对象）调用前的逻辑
   *    2. @Before
   *    3. 目标方法调用
   *    4. @AfterReturning
   *    5. @After
   *    6. @Around 方法（非目标方法，是 AOP 包装后的代理对象）调用后的逻辑
   * 不同切面内 同一类型的通知方式执行顺序是明确的，如下所示：
   *    1. 按照 Spring Order 决定
   *    2. 具体实现 参见如下路径代码：
   *        2.1 org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator#sortAdvisors(java.util.List) 排序
   *        2.2 org.springframework.aop.aspectj.annotation.BeanFactoryAspectJAdvisorsBuilder#buildAspectJAdvisors()
   *            按切面顺序（容器依赖查找，此时顺序已经决定好了）构建 Advisor
   * @param args
   */
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AspectAdviceExecuteOrderDemo.class);
    context.refresh();

    EchoService echoService = context.getBean("echoService", EchoService.class);
    System.out.println(echoService.echo("Hello AOP"));

    context.close();
  }

  @Bean
  public EchoService echoService() {
    return new DefaultEchoService();
  }
}
