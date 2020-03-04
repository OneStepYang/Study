package com.harvey.work.basics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: Study
 * @ClassName : StudyProxy
 * @description: 学习代理
 * @author: Harvey
 * @create: 2020-02-23 18:15
 */
public class StudyProxy {
    public static void main(String[] args) {
        Me me = new Me();
        InvocationHandler proxyHandler = new ProxyHandler(me);
        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, proxyHandler);
        person.eat("饭");
        System.out.println(person);
        //person.say("hello!");
    }

}