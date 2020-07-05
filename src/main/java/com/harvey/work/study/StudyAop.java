package com.harvey.work.study;

import org.springframework.context.annotation.ComponentScan;

public class StudyAop {
    public static void main(String[] args) {
        HelloWorldImpl1 helloWorldImpl1 = new HelloWorldImpl1();
        helloWorldImpl1.doPrint();
    }
}

