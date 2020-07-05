package com.harvey.work.study;

public class Study {
}

interface interface2 {
    int l = 3;
    int k = 4;

    void eat();
}
interface interface3 {
    int l = 3;
    int k = 4;

    void eat();
}

interface interface1 extends interface2,interface3 {
    int l = 3;
    int k = 4;

    void eat();
}

abstract class absClass {
    private int i = 1;
    protected  int j = 2;
    int l = 3;
    public int k = 4;
    abstract void eat();

    public static void main(String[] args) {

    }

    public void sleep(){

    }

    private void watch(){

    }

    protected void listen(){

    }


}