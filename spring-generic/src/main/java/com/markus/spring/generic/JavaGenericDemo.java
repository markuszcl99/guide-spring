package com.markus.spring.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: markus
 * @date: 2023/12/30 10:03 PM
 * @Description: Java 泛型示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class JavaGenericDemo {
    public static void main(String[] args) {
        // java 7 diamond 语法
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Spring");

//        编译时 强校验 参数类型
//        list.add(1);

        // 骗过编译器 将 int类型值 添加到 list 中去
        Collection temp = list;
        temp.add(1);

        System.out.println(list);
    }
}
