package com.markus.spring.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: markus
 * @date: 2024/2/3 10:19 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class TransactionPropagationService {

    // 事务传播行为 默认 REQUIRED
    @Transactional
    public void method() {
        methodA();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void methodA() {
        System.out.println("methodA invoke... ");
    }
}
