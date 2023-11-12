package com.markus.spring.mvc.service.impl;

import com.markus.spring.mvc.service.Action;

/**
 * @author: markus
 * @date: 2023/11/11 10:34 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ActionImpl implements Action {
    @Override
    public void doAction() {
        System.out.println("real action ...");
    }
}
