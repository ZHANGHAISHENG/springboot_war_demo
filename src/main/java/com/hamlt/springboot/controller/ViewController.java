package com.hamlt.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * springboot 不建议使用jsp
 */
@RestController
@RequestMapping(value="/view")
public class ViewController {

    //jsp视图测试
    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public ModelAndView helloJsp()  {
        return new ModelAndView("hello");
    }

}
