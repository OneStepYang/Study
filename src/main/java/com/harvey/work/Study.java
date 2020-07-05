package com.harvey.work;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Study {
    public static void main(String[] args) {
        Fooaa<String> foo = new Fooaa<String>() {
        };
        // 在类的外部这样获取
        System.out.println((ParameterizedType)foo.getClass().getGenericSuperclass());
        Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);
        // 在类的内部这样获取
        System.out.println(foo.getTClass());
    }
}

abstract class Foo<T> {
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}

abstract class Fooaa<T> extends Foo<T> {
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}