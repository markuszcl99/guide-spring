package com.markus.spring.i18n;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/20
 * @Description:
 */
public class PathMatchingResourcePatternResolverDemo {
  public static void main(String[] args) throws IOException {
    PathMatchingResourcePatternResolver resolver =
        new PathMatchingResourcePatternResolver(PathMatchingResourcePatternResolverDemo.class.getClassLoader());
    Resource[] resources = resolver.getResources("classpath*:/META-INF/messages_*.properties");
    for (Resource resource : resources) {
      System.out.println(resource.getDescription());
    }
  }
}
