package com.harvey.work.basics;

public class StudyArray {
    public static void main(String[] args) {
        Penguin penguin = new Penguin("yy","1");
        penguin.sleep("yhw");
    }
}

class ChangeIt {
    static void doIt(String[] z) {
        z = null;
    }
}


class Animal {
    private String name;
    private int id;

    public Animal(String myName, String myid) {
        //初始化属性值
    }

    private void eat() {
        System.out.println("eat!");
    }

    public void sleep() {
        System.out.println("sleep!");
    }
}

class Penguin extends Animal {
    public Penguin(String myName, String myid) {
        super(myName, myid);
    }

    @Override
    public void sleep() {
        System.out.println("Penguin sleep!");
    }

    public void sleep(String name){
        System.out.println(name + " had sleeped!");
    }



}
