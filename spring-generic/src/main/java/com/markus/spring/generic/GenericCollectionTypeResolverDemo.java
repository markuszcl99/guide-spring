package com.markus.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/3
 * @Description: Java 复合类型泛型示例 {@link GenericCollectionTypeResolver}
 * @see GenericCollectionTypeResolver
 */
public class GenericCollectionTypeResolverDemo {

  private Map<String, Integer> map;

  private Collection<Number> numbers;

  public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
    // 获取集合元素的类型
    Class<?> collectionType = GenericCollectionTypeResolver.getCollectionType(StringList.class);
    System.out.printf("genericCollectionTypeResolver#getCollectionType(%s) : %s\n", StringList.class, collectionType);

    // 泛型类获取不到元素类型数据（泛型擦除的原因）
    Class<?> mapKeyType = GenericCollectionTypeResolver.getMapKeyType(HashMap.class);
    System.out.printf("genericCollectionTypeResolver#getMapKeyType(%s) : %s\n", HashMap.class, mapKeyType);

    // 字节码会保留 泛型类型具体化后的信息
    mapKeyType = GenericCollectionTypeResolver.getMapKeyType(CustomizedMap.class);
    System.out.printf("genericCollectionTypeResolver#getMapKeyType(%s) : %s\n", CustomizedMap.class, mapKeyType);

    // 获取 map class 的 value 元素类型
    Class<?> mapValueType = GenericCollectionTypeResolver.getMapValueType(CustomizedMap.class);
    System.out.printf("genericCollectionTypeResolver#getMapValueType(%s) : %s\n", CustomizedMap.class, mapValueType);

    // 获取给定 map field 的 key value 元素类型
    Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("map");
    Class<?> mapKeyFieldType = GenericCollectionTypeResolver.getMapKeyFieldType(field);
    System.out.printf("genericCollectionTypeResolver#getMapKeyFieldType(%s) : %s\n", field, mapKeyFieldType);
    Class<?> mapValueFieldType = GenericCollectionTypeResolver.getMapValueFieldType(field);
    System.out.printf("genericCollectionTypeResolver#getMapValueFieldType(%s) : %s\n", field, mapValueFieldType);

    // 获取给定 collection field 的 元素类型
    field = GenericCollectionTypeResolverDemo.class.getDeclaredField("numbers");
    Class<?> collectionFieldType = GenericCollectionTypeResolver.getCollectionFieldType(field);
    System.out.printf("genericCollectionTypeResolver#getCollectionFieldType(%s) : %s\n", field, collectionFieldType);

    // 获取给定 method 的具体索引位置的参数类型
    Method method = GenericCollectionTypeResolverDemo.class.getDeclaredMethod("method", List.class, List.class);
    MethodParameter methodParameter = new MethodParameter(method, 0);
    Class<?> collectionParameterType = GenericCollectionTypeResolver.getCollectionParameterType(methodParameter);
    System.out.printf("genericCollectionTypeResolver#getCollectionParameterType(%s) : %s\n", methodParameter, collectionParameterType);

    methodParameter = new MethodParameter(method, 1);
    collectionParameterType = GenericCollectionTypeResolver.getCollectionParameterType(methodParameter);
    System.out.printf("genericCollectionTypeResolver#getCollectionParameterType(%s) : %s\n", methodParameter, collectionParameterType);
  }

  private void method(List<Integer> a, List<String> b) {

  }
}
