package com.markus.spring.data.jdbc.domain.entity;

import java.io.Serializable;

/**
 * @author: markus
 * @date: 2024/2/1 11:09 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class User implements Serializable {
    private int id;
    private int age;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime : " + currentTime);
        user.setName("帅气的小张 " + currentTime);
        user.setAge(25);
        user.setAddress("山东菏泽");
        return user;
    }
}
