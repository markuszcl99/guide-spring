package com.markus.spring.aop.features.advisor;

import com.markus.spring.aop.overview.EchoService;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/26
 * @Description:
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {
  public static void main(String[] args) {
    IntroductionAdvisorDemo introductionAdvisorDemo = new IntroductionAdvisorDemo();

    // 调用下面这个构造器去创建 ProxyFactory 会使得 IntroductionInfo 失效，即代理接口不受 IntroductionInfo 控制了
    // ProxyFactory proxyFactory = new ProxyFactory(introductionAdvisorDemo);
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(introductionAdvisorDemo);
    DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Method Before : " + method);
      }
    }, new IntroductionInfo() {
      @Override
      public Class<?>[] getInterfaces() {
        return new Class[]{EchoService.class, Map.class, Comparable.class};
      }
    });
    proxyFactory.addAdvisor(advisor);


    Object proxy = proxyFactory.getProxy();
    EchoService echoService = (EchoService) proxy;
    echoService.echo("Hello,IntroductionInfo");

    Map map = (Map) proxy;
    map.put("1,", 1);

    Comparable comparable = (Comparable) proxy;
    comparable.compareTo(new Object());
  }

  @Override
  public String echo(String message) {
    System.out.printf("Introduction Advisor Demo Message : %s\n", message);
    return message;
  }

  @Override
  public int compareTo(IntroductionAdvisorDemo o) {
    return 0;
  }
}
