package com.markus.spring.annotation;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/1
 * @Description:
 */
public class FindAnnotationMethodDemo {
  public static void main(String[] args) throws NoSuchMethodException {
    Method method = FindAnnotationMethodDemo.class.getDeclaredMethod("user");
    Scope mergedAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, Scope.class);
    System.out.println(mergedAnnotation);
  }

  @Scope("prototype")
  public User user() {
    return User.createUser();
  }
}
