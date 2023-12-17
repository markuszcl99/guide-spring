package com.markus.spring.bean.scope.web.controller;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: markus
 * @date: 2023/12/17 11:55 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Controller
public class IndexController {

    @Autowired
    private User user; // Cglib 代理过后的对象 不会变化
//    <%-- jsp 的变量搜索路径 page -> request -> session -> application(ServletContext) --%>

//    @GetMapping("/index.html")
//    public String home(Model mv) {
//        mv.addAttribute("userObject", user);
//        return "index";
//    }

    @GetMapping("/index.html")
    public ModelAndView modelAndView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("userObject", user);
        return mv;
    }
}
