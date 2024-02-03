package com.markus.spring.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: markus
 * @date: 2024/2/3 10:37 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class NotSupportsPropagationService {

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void method() {
        System.out.println("NotSupportsPropagationService method invoke");
    }
}
