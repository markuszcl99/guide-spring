package com.markus.spring.resource;

import com.markus.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/6
 * @Description: 注入 Resource
 * @see Value
 * @see Resource
 */
public class InjectionResourceDemo {

  @Value("classpath:META-INF/default.properties")
  private Resource resource;

  /**
   * 找到当前类路径下所有的META-INF目录，然后通过ant模型对*.properties进行匹配
   */
  @Value("classpath*:META-INF/*.properties")
  private Resource[] resources;

  /**
   * 找到当前类路径下第一个META-INF目录，然后通过该目录根据ant模型对*.properties进行匹配，所以匹配不到
   */
  @Value("classpath:META-INF/*.properties")
  private Resource[] errorUseValueResources;

  @Value("${user.dir}")
  private String currentUserPath;

  @PostConstruct
  public void init() {
    System.out.println(ResourceUtils.getContent(resource));
    System.out.println("=================");
    Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    System.out.println("=================");
    System.out.println(currentUserPath);
    System.out.println("=================");
    Stream.of(errorUseValueResources).map(ResourceUtils::getContent).forEach(System.out::println);
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(InjectionResourceDemo.class);
    context.refresh();
    context.close();
  }
}
