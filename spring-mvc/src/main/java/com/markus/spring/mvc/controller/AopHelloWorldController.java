package com.markus.spring.mvc.controller;

import com.markus.spring.mvc.service.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: markus
 * @date: 2023/11/11 10:32 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestController
@RequestMapping("/aop")
public class AopHelloWorldController {

    @Autowired
    private Action action;

    @RequestMapping("/action")
    public String action() {
        action.doAction();
        return "test aop success";
    }
}
