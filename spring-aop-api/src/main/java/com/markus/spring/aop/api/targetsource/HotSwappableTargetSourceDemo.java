package com.markus.spring.aop.api.targetsource;

import com.markus.spring.aop.overview.DefaultEchoService;
import com.markus.spring.aop.overview.EchoService;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/18
 * @Description: {@link HotSwappableTargetSource} 示例
 * @see HotSwappableTargetSource
 */
public class HotSwappableTargetSourceDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/hot-swappable-target-source.xml");

    HotSwappableTargetSource swapper = context.getBean("swapper", HotSwappableTargetSource.class);
    EchoService target = (EchoService) swapper.getTarget();
    System.out.println(target.echo("Hello,TargetSource"));
    // 运行时替换
    swapper.swap(new DefaultEchoService());
  }
}
