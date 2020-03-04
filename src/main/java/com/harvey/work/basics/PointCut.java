package com.harvey.work.basics;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: Study
 * @ClassName : PointCut
 * @description: 切面功能增强类
 * @author: Harvey
 * @create: 2020-02-24 15:30
 */
@Component
@Aspect
public class PointCut {
    //匹配ProductServiceImpl类里面的所有方法
    //@Pointcut("within(com.aop.service.impl.ProductServiceImpl)")
    //匹配com.aop.service.impl.ProductServiceImpl类下的方法名以delete开头、参数类型为Long的public方法
    //访问修饰符，返回值类型，包路径，
    //(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)
    //@Pointcut("execution(public * com.harvey.work.service.impl.UserServiceImpl.*  (..))")
    @Pointcut("execution(* com.harvey.work.service.impl.UserServiceImpl.* (..))")
    public void testAop(){
        System.out.println("");
    }

    @Before("testAop()")
    public void beforeAop(){
        System.out.println("Aop前置通知!");
    }
    @After("testAop()")
    public void afterAop(){
        System.out.println("Aop后置通知！");
    }

}