package com.harvey.work.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudyProxy {
    public static void main(String[] args) {
        Man man = new Man();
        //静态
        ManProxy manProxy = new ManProxy();
        manProxy.setTarget(man);
        manProxy.say();
        //动态
        InvocationHandler normalHandler = new NormalHandler(man);
        //IPerson personProxy = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(), new Class<?>[]{IPerson.class}, normalHandler);        /** 代理类信息 */
        IPerson iPerson = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(),new Class[] {IPerson.class}, normalHandler);
        iPerson.say();
    }
}

//基础类
interface IPerson {
    void say();
}

class Man implements IPerson {
    @Override
    public void say() {
        System.out.println("man say");
    }
}
//静态代理
class ManProxy implements IPerson {

    private IPerson target;

    public IPerson getTarget() {
        return target;
    }

    public ManProxy setTarget(IPerson target) {
        this.target = target;
        return this;
    }

    @Override
    public void say() {
        if (target != null) {
            System.out.println("静态代理 at : " + System.currentTimeMillis());
            target.say();
        }
    }
}

//动态代理
class NormalHandler implements InvocationHandler {

    private Object target;

    public NormalHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理 at : " + System.currentTimeMillis());
        method.invoke(target, args);
        return null;
    }
}