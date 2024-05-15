package com.markus.spring.application.context.lifecycle.service;

import com.markus.spring.annotation.annotation.OperatorScan;
import com.markus.spring.application.context.lifecycle.config.BeanConfiguration2;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: markus
 * @date: 2024/5/15 12:32 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Import(BeanConfiguration2.class)
public class EchoService implements ImportAware {
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        // do something
        System.out.println("echo");
    }
}
