package com.markus.spring.expression.language;

import java.util.Date;
import java.util.List;

/**
 * @author: markus
 * @date: 2024/1/21 3:38 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Inventor {
    private String name;
    private Integer age;
    private Date birthday;
    private String nationality;

    private List<Boolean> booleans;

    public Inventor() {
    }

    public Inventor(String name, Date birthday, String nationality) {
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
    }

    public Inventor(String name, Date birthday, String nationality, List<Boolean> booleans) {
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
        this.booleans = booleans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Boolean> getBooleans() {
        return booleans;
    }

    public void setBooleans(List<Boolean> booleans) {
        this.booleans = booleans;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Inventor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                ", booleans=" + booleans +
                '}';
    }
}
