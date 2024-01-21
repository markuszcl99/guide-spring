package com.markus.spring.expression.language;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author: markus
 * @date: 2024/1/21 9:06 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class InventorBuilder {
    public static Inventor builder() {
        Inventor inventor = new Inventor();
        inventor.setName("markus zhang");
        inventor.setAge(24);
        inventor.setNationality("China");
        inventor.setBooleans(new ArrayList<>());
        GregorianCalendar c = new GregorianCalendar();
        c.set(1999, Calendar.FEBRUARY, 29);
        inventor.setBirthday(c.getTime());
        return inventor;
    }

    public static Inventor builder(String name) {
        Inventor inventor = new Inventor();
        inventor.setName(name);
        inventor.setAge(24);
        inventor.setNationality("China");
        inventor.setBooleans(new ArrayList<>());
        GregorianCalendar c = new GregorianCalendar();
        c.set(1999, Calendar.FEBRUARY, 29);
        inventor.setBirthday(c.getTime());
        return inventor;
    }
}
