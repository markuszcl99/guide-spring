package com.markus.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.stream.Stream;

import static org.springframework.core.GenericTypeResolver.*;

/**
 * @author: markus
 * @date: 2024/1/2 10:36 PM
 * @Description: Spring 泛型辅助类 {@link GenericTypeResolver} 使用示例
 * @Blog: https://markuszhang.com
 * @see GenericTypeResolver
 * It's my honor to share what I've learned with you!
 */
public class GenericTypeResolverDemo<T> {
    /**
     * 泛型辅助类 GenericTypeResolver 的几个接口
     * <p>
     * resolveParameterType :
     * </p>
     * <p>
     * resolveReturnType
     * </p>
     * <p>
     * resolveReturnTypeArgument :
     * </p>
     * <p>
     * resolveTypeArgument :
     * </p>
     * <p>
     * resolveTypeArguments :
     * </p>
     */

    private List<T> myList;

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        // String 是 Comparable<String> 的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

        Type genericType = GenericTypeResolverDemo.class.getDeclaredField("myList").getGenericType();
        Type type = resolveType(genericType, GenericTypeResolverDemo.class);
        System.out.println(type);

        // 解析出泛型类型参数
        Class<?> typeArgument = resolveTypeArgument(StringList.class, List.class);
        System.out.println(typeArgument);

        Class<?>[] resolveTypeArguments = resolveTypeArguments(StringList.class, List.class);
        if (resolveTypeArguments != null) {
            Stream.of(resolveTypeArguments).forEach(System.out::println);
        }

        // 拿不到 泛型参数类型，因为没有具体化，字节码中不存在
        resolveTypeArguments = resolveTypeArguments(HashMap.class, Map.class);
        if (resolveTypeArguments != null) {
            Stream.of(resolveTypeArguments).forEach(System.out::println);
        }

        // 解析泛型类型变量 map（会递归查找该类的继承体系，将有泛型的类进行存储）
        Map<TypeVariable, Type> typeVariableMap = getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
        typeVariableMap = getTypeVariableMap(CustomizedMap.class);
        System.out.println(typeVariableMap);
    }

    private static String getString() {
        return null;
    }

    private static StringList getStringList() {
        return null;
    }

    private static ArrayList<Object> getList() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class<?>... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getDeclaredMethod(methodName, argumentTypes);

        Class<?> returnType = resolveReturnType(method, genericIfc);

        // 常规类 作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

        // 常规类 不具备泛型参数类型 List<E>
        Class<?> returnTypeArgument = resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n", methodName, genericIfc.getSimpleName(), returnTypeArgument);
    }

    static class StringList extends ArrayList<String> {

    }

    static class CustomizedMap extends HashMap<String, Integer> {

    }
}
