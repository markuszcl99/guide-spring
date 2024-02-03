package com.markus.spring.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: markus
 * @date: 2024/2/3 10:35 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class NeverPropagationService {

//    @Autowired
//    private RequiredPropagationService requiredPropagationService;

    @Transactional(propagation = Propagation.NEVER)
    public void method() {
        System.out.println("NeverPropagationService method invoke...");
        // invoke transaction method
//        requiredPropagationService.method();
    }
}
