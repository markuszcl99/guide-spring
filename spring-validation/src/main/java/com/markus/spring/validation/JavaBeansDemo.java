package com.markus.spring.validation;

import com.markus.spring.ioc.overview.domain.User;

import java.beans.*;
import java.util.Arrays;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/27
 * @Description:
 */
public class JavaBeansDemo {
  public static void main(String[] args) throws IntrospectionException {
    // param 1. target class
    // param 2. stop Class
    BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);

    // java bean 信息描述
    BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
    System.out.println(beanDescriptor);

    // java bean 属性描述
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
    Arrays.stream(propertyDescriptors).forEach(System.out::println);

    // java bean 方法描述
    MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
    Arrays.stream(methodDescriptors).forEach(System.out::println);
  }
}
