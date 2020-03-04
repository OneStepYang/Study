package com.harvey.work.basics;

/**
 * @program: Study
 * @ClassName : Me
 * @description: 我是个人
 * @author: Harvey
 * @create: 2020-02-23 18:14
 */
public class Me implements  Person{
    @Override
    public void say(String someThing) {
        System.out.println("我说"+someThing);
    }

    @Override
    public void eat(String someThing) {
        System.out.println("我吃"+someThing);
    }

    @Override
    public void sleep() {
        System.out.println("我睡觉了");
    }

    @Override
    public String toString() {
        System.out.println("Call My toString!");
        return "Me toString!";
    }
}