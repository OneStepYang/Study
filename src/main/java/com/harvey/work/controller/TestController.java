package com.harvey.work.controller;

import com.harvey.work.study.HelloWorldImpl1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/aop")
    public String testAop(){
        HelloWorldImpl1 helloWorldImpl1 = new HelloWorldImpl1();
        helloWorldImpl1.doPrint();
        return "true";
    }

}
