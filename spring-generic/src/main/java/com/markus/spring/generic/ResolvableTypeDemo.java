package com.markus.spring.generic;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/4
 * @Description: Spring 4 提出的 {@link ResolvableType} 示例
 * @see ResolvableType
 */
public class ResolvableTypeDemo {

    Map<String, List<Integer>> map;

    Map<? extends String, ? extends Integer> wildcardMap;

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        System.out.printf("ResolvableType.forClass(%s) : %s\n", StringList.class, resolvableType);
        ResolvableType generic = resolvableType.getGeneric(1);
        System.out.println(generic);
        ResolvableType superType = resolvableType.getSuperType();
        System.out.println(superType);
        Class<?> rawClass = superType.getRawClass();
        System.out.println(rawClass);
        generic = superType.getGeneric(0);
        System.out.println(generic.getRawClass().cast("123"));

        Class<?> resolve = resolvableType.getSuperType().getGeneric(0).resolve();
        System.out.println(resolve);

        resolvableType = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("map"));
        resolve = resolvableType.resolve();
        System.out.println(resolve);

        resolve = resolvableType.getGeneric(0).resolve();
        System.out.println(resolve);

        resolve = resolvableType.getGeneric(1).resolve();
        System.out.println(resolve);

        resolve = resolvableType.getGeneric(1, 0).resolve();
        System.out.println(resolve);

        resolvableType = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("wildcardMap"));
        resolve = resolvableType.resolve();
        System.out.println("wildcardMap resolvableType : " + resolve);
        resolve = resolvableType.asMap().resolve();
        System.out.println("wildcardMap resolvableType.asMap() : " + resolve);
        resolve = resolvableType.getGeneric(0).resolve();
        System.out.println("wildcardMap resolvableType.getGeneric(0) : " + resolve);
        resolve = resolvableType.getGeneric(1).resolve();
        System.out.println("wildcardMap resolvableType.getGeneric(1) : " + resolve);

        Method method = ResolvableTypeDemo.class.getDeclaredMethod("getObject");
        resolvableType = ResolvableType.forMethodReturnType(method);
        System.out.println("泛型方法未指定泛型参数边界 : " + resolvableType.resolve());

        method = ResolvableTypeDemo.class.getDeclaredMethod("getGenericBoundObject");
        resolvableType = ResolvableType.forMethodReturnType(method);
        System.out.println("泛型方法指定泛型参数边界 : " + resolvableType.resolve());

        method = ResolvableTypeDemo.class.getDeclaredMethod("getConcreteObject");
        resolvableType = ResolvableType.forMethodReturnType(method);
        System.out.println("具体方法 : " + resolvableType.resolve());

    }

    public <T> T getObject() {
        return null;
    }

    public <T extends String> T getGenericBoundObject() {
        return null;
    }

    public String getConcreteObject() {
        return null;
    }

}
