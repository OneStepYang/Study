package com.harvey.work.basics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: Study
 * @ClassName : ProxyHandler
 * @description: 代理人
 * @author: Harvey
 * @create: 2020-02-23 18:17
 */
public class ProxyHandler  implements InvocationHandler {
    Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(target, args);
        after();
        return object;
    }

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }
}