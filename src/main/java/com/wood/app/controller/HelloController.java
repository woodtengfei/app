package com.wood.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: Hello服务测试
 * @Author wood
 * @Date 2020-12-09
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView hello(String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name == null ? "" : name);
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

}
