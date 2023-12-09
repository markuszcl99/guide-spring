package com.markus.spring.annotation.proxy;

/**
 * @author: markus
 * @date: 2023/12/9 11:59 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sayHello() {
        System.out.println("Hello, I'm " + name);
    }
}
