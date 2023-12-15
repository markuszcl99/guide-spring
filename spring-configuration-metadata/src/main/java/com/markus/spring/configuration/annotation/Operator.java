package com.markus.spring.configuration.annotation;

import java.lang.annotation.*;

/**
 * @author: markus
 * @date: 2023/12/15 11:05 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operator {
    String value() default "";
}
