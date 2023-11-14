package com.markus.guide.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: markus
 * @date: 2023/11/14 8:05 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("hello Spring Mvc!");
    }
}
