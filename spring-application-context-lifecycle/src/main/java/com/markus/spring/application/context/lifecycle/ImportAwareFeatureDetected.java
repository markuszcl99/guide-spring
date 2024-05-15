package com.markus.spring.application.context.lifecycle;

import com.markus.spring.application.context.lifecycle.config.BeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: markus
 * @date: 2024/5/15 12:33 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ImportAwareFeatureDetected {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfiguration.class);
        context.refresh();
        context.close();
    }
}
