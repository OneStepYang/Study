package com.harvey.work.basics;

import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: Study
 * @ClassName : StudyArrayList
 * @description: 学习ArrayList
 * @author: Harvey
 * @create: 2020-02-27 15:01
 */
public class StudyArrayList {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
       /* ArrayList<Integer> list = new ArrayList<>();
        Class c= ArrayList.class;
        Field modCountField = c.getDeclaredField("modCount");
        modCountField.setAccessible(true);
        for (int i = 0; i < 5; i++) {
            list.add(i);
            System.out.println(modCountField.get(list));
        }
        Field[] fields = list.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.println(fields[i].getName());
        }*/
        //field.setAccessible(true);
        //System.out.println(field.get(list).toString());

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Field field = hashMap.getClass().getDeclaredField("threshold");
        Field field2 = hashMap.getClass().getDeclaredField("table");
        field2.setAccessible(true);
        field.setAccessible(true);
        for (int i = 0; i < 16; i++) {
            hashMap.put(i, i);
            System.out.println(field.get(hashMap));
        }
    }
}