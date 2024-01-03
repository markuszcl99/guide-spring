package com.markus.spring.generic;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  List<? extends Number> numbers;

  Map.Entry<String, String> mapEntry;

  public static void main(String[] args) throws NoSuchFieldException {
    // 1. raw types : 原生类型 eg. List<Integer> 中的 List 就是原生类型，简而言之就是没有泛型的非数组、原始类型都是原生类型
    Class<?> listClass = ArrayList.class;
    System.out.println("raw types : " + listClass);

    // 2. array types : 数组类型
    Class<?> arrayClass = int[].class;
    System.out.println("array types : " + arrayClass);

    // 3. parameterized types : 泛型参数类型
    ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
    System.out.println("parameterized types : " + parameterizedType);

    // 4. type variable : 泛型类型变量
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    System.out.print("AbstractList type arguments : ");
    Stream.of(actualTypeArguments)
        .map(TypeVariable.class::cast) // Type --> TypeVariable
        .forEach(System.out::println);

    parameterizedType = (ParameterizedType) HashMap.class.getGenericSuperclass();
    actualTypeArguments = parameterizedType.getActualTypeArguments();
    System.out.print("Map type arguments : ");
    Stream.of(actualTypeArguments)
        .map(TypeVariable.class::cast) // Type --> TypeVariable
        .forEach(typeVariable -> System.out.print(typeVariable + " "));

    // 5. generic array types : 泛型数组类型
    Field field = GenericAPIDemo.class.getDeclaredField("lists");
    GenericArrayType genericType = (GenericArrayType) field.getGenericType();
    System.out.printf("\ngeneric array type : %s \n", genericType);
    parameterizedType = (ParameterizedType) genericType.getGenericComponentType();
    System.out.println("ParameterizedType.getRawType : " + parameterizedType.getRawType());
    System.out.println("ParameterizedType.getOwnerType : " + parameterizedType.getOwnerType());
    actualTypeArguments = parameterizedType.getActualTypeArguments();
    System.out.print("concrete type arguments : ");
    Stream.of(actualTypeArguments)
        .map(Class.class::cast) // 参数类型具体化
        .forEach(clazz -> System.out.print(clazz + " "));

    field = GenericAPIDemo.class.getDeclaredField("mapEntry");
    parameterizedType = (ParameterizedType) field.getGenericType();
    System.out.println("\nMap.Entry<String, String> ownerType : " + parameterizedType.getOwnerType());


    // 6. wildcard types : 泛型通配符类型
    field = GenericAPIDemo.class.getDeclaredField("numbers");
    parameterizedType = (ParameterizedType) field.getGenericType();
    WildcardType wildcardType = (WildcardType) parameterizedType.getActualTypeArguments()[0];
    // ? extends Number
    System.out.println("WildcardType : " + wildcardType);
    // Number
    System.out.println("WildcardType's UpperBounds : " + wildcardType.getUpperBounds()[0]);
  }
}
