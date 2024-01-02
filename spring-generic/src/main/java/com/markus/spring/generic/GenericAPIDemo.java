package com.markus.spring.generic;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/2
 * @Description: Java 泛型 示例
 */
public class GenericAPIDemo {
  /**
   * note:
   * <p>
   * Java中的Class包括: 原生类型（raw type）、数组类型（array type）、原始类型（primitive type）、 泛型参数类型（parameterized type)
   * 以及 泛型类型变量（type variable）
   */

  List<String>[] lists;

  public static void main(String[] args) throws NoSuchFieldException {
    // 1. raw types : 原生类型 eg. List<Integer> 中的 List 就是原生类型，简而言之就是没有泛型的非数组、原始类型都是原生类型
    Class<?> listClass = ArrayList.class;
    System.out.println(listClass);

    // 2. array types : 数组类型
    Class<?> arrayClass = int[].class;
    System.out.println(arrayClass);

    // 3. parameterized types : 泛型参数类型
    ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
    System.out.println(parameterizedType);

    // 4 generic array types : 泛型数组类型
    Field field = GenericAPIDemo.class.getDeclaredField("lists");
    Type genericType = field.getGenericType();
    System.out.println(genericType);

    // 5. type variable : 泛型类型变量
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

    Stream.of(actualTypeArguments)
        .map(TypeVariable.class::cast) // Type --> TypeVariable
        .forEach(System.out::println);
  }
}
