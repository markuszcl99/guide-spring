package com.markus.spring.resource;

import com.markus.spring.resource.util.ResourceUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/6
 * @Description: 自定义resource 模式匹配并解析出Resource
 */
public class CustomizedResourcePatternResolverDemo {

  /**
   * note:
   * <p> 区别Resource各种资源的使用场景，这里我用了绝对路径并采用了默认资源加载器，则会报错，采用文件系统资源加载器则正常
   */
  public static void main(String[] args) throws IOException {
    String currentPath = "/" + System.getProperty("user.dir") + "/spring-resource/src/main/java/com/markus/spring/resource/";

    // 默认是Ant匹配模式
    PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
    // 自定义模式匹配器
    // resourcePatternResolver.setPathMatcher(new CustomPathMatcher());

    Resource[] resources = resourcePatternResolver.getResources(currentPath + "*.java");
    Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
  }

  static class CustomPathMatcher implements PathMatcher {

    @Override
    public boolean isPattern(String path) {
      return path.endsWith(".java");
    }

    @Override
    public boolean match(String pattern, String path) {
      return path.endsWith(".java");
    }

    @Override
    public boolean matchStart(String pattern, String path) {
      return false;
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
      return null;
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
      return null;
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
      return null;
    }

    @Override
    public String combine(String pattern1, String pattern2) {
      return null;
    }
  }
}
