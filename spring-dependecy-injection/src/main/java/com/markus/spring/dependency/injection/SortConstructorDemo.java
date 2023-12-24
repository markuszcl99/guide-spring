package com.markus.spring.dependency.injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: markus
 * @date: 2023/12/24 3:59 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SortConstructorDemo {

    A a;
    B b;

    public SortConstructorDemo() {

    }

    public SortConstructorDemo(B b, A a) {
        this.a = a;
        this.b = b;
    }

    public SortConstructorDemo(A a, B b) {
        this.a = a;
        this.b = b;
    }

    static class A {

    }

    static class B {

    }

    public static final Comparator<Executable> EXECUTABLE_COMPARATOR = (e1, e2) -> {
        int result = Boolean.compare(Modifier.isPublic(e2.getModifiers()), Modifier.isPublic(e1.getModifiers()));
        return result != 0 ? result : Integer.compare(e2.getParameterCount(), e1.getParameterCount());
    };

    public static void main(String[] args) {
        Constructor<?>[] declaredConstructors = SortConstructorDemo.class.getDeclaredConstructors();
        Arrays.sort(declaredConstructors, EXECUTABLE_COMPARATOR);
        System.out.println();
    }
}
