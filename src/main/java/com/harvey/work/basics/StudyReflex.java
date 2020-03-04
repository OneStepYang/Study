package com.harvey.work.basics;

/**
 * @program: Study
 * @ClassName : StudyReflex
 * @description: 学习Java高级特性——反射
 * @author: Harvey
 * @create: 2020-03-02 16:45
 */
public class StudyReflex {

    public static void main(String[] args) {

        System.out.println(String.class.getSimpleName());
        System.out.println(String.class.getPackage());
        System.out.println(String.class.getSuperclass());
        System.out.println(String.class.getInterfaces().length);
        System.out.println(String.class.getClassLoader());
        System.out.println(String.class.getName());
        System.out.println(String.class.getMethods().length);
        System.out.println(String.class.getDeclaredMethods().length);
    }
}