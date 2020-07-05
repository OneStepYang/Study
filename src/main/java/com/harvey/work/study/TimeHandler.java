package com.harvey.work.study;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeHandler {

    @Pointcut(value="execution(* com.harvey.work.controller.TestController.*(..))")
    public void message(){

    }
    @Around("message()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕----前----CurrentTime = " + System.currentTimeMillis());
        Object obj =  joinPoint.proceed();
        System.out.println("环绕----后----CurrentTime = " + System.currentTimeMillis());
        return obj;
    }

    @Before("message()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("代理----前： " + joinPoint.getTarget());

    }

    @After("message()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("代理----后： " + joinPoint.getTarget());
    }

    @AfterReturning("message()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        System.out.println("代理----返回后----CurrentTime = " + System.currentTimeMillis());
    }

}