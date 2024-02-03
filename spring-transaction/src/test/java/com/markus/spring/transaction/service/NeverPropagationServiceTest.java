package com.markus.spring.transaction.service;

import com.markus.spring.transaction.config.TransactionModuleApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: markus
 * @date: 2024/2/3 10:22 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionModuleApplicationConfig.class)
public class NeverPropagationServiceTest {

    @Autowired
    private NeverPropagationService neverPropagationService;

    @Test
    public void testMethod() {
        neverPropagationService.method();
    }
}
