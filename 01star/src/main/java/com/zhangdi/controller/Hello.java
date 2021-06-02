package com.zhangdi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class Hello {

    @RequestMapping(value = "/word")
    public String sayHelloWord(){
        return "hello word";
    }
}
