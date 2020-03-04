package com.harvey.work.basics;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class StudyThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //三段代码逐一测试

        //测试tryLock
        Thread threadTest00 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!lock.tryLock()){
                        System.out.println(Thread.currentThread().getName() + "没有拿到锁，继续等待！");
                        Thread.sleep(50);
                    }
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块！");
                    Thread.currentThread().sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest00.start();
        Thread threadTest01 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!lock.tryLock()){
                        System.out.println(Thread.currentThread().getName() + "没有拿到锁，继续等待！");
                        Thread.sleep(50);
                    }
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块！");
                    Thread.currentThread().sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest01.start();

        //测试中断锁
        Thread threadTest02 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块！");
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest02.start();
        Thread threadTest03 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块！");
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest03.start();
        Thread.currentThread().sleep(20);
        threadTest02.interrupt();


        Thread threadTest04 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块，等待通知！");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "收到通知，继续执行！");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest04.start();
        Thread threadTest05 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "进入了lock代码块！");
                    Thread.currentThread().sleep(1000);
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "发出通知！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "准备释放锁，并离开lock代码块！");
                    lock.unlock();
                }
            }
        });
        threadTest05.start();
    }

    public int add(int a,int b) throws NullPointerException{
        return a+b;
    }

}
