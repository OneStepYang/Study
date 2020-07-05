package com.harvey.work;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        String[] s = in.nextLine().split(",");

        Double x = Double.valueOf(s[0]);
        Double y = Double.valueOf(s[1]);
        if(x>y){
            double temp = x;
            x = y;
            y = temp;
        }
        if(Math.abs(x-y)<1){
            System.out.println("NO DATA");
            return;
        }
        a = (int) Math.ceil(x);
        b = (int) Math.floor(y);
        System.out.println(a+","+b);
        ArrayList list = new ArrayList();
        boolean flag = true;
        for(int i = a;i<=b;i++){
            for(int j = 2;j<i;j++){
                if(i%j == 0){
                    flag = false;
                }
            }
            if(flag){
                list.add(i);
            }
        }
        if(list.size()>0){
            for (Object i :list) {
                System.out.print(i+",");
            }
        }else{
            System.out.println("NO DATA");
        }


    }

}