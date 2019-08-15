package com.sgz;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;


/**
 * @Description:
 * 假设你的程序中涉及到对一些共享资源的读和写操作，且写操作没有读操作那么频繁。在没有写操作的时候，两个线程同时读一个资源没有任何问题，
 * 所以应该允许多个线程能在同时读取共享资源。但是如果有一个线程想去写这些共享资源，就不应该再有其它线程对该资源进行读或写（译者注：也就是说：读-读能共存，读-写不能共存，写-写不能共存）
 * @Author: shigzh
 * @CreateDate: 2019/8/15 11:26
 */
public class ReentrantReadWriteLockDemo {
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReadLock readLock = rwLock.readLock();
    private WriteLock writeLock = rwLock.writeLock();

    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName()+"读进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程："+Thread.currentThread().getName()+"读退出。。。");
        } catch (Exception e){

        } finally {
            readLock.unlock();
        }
    }

    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName()+"写进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程："+Thread.currentThread().getName()+"写退出。。。");
        } catch (Exception e){

        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo reentrantReadWriteLockDemo = new ReentrantReadWriteLockDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockDemo.read();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockDemo.read();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockDemo.write();
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockDemo.write();
            }
        },"t4");
        t1.start();
        t2.start();
 //       t3.start();
//        t4.start();
    }
}
//代码分析：
//        t1,t2线程执行读操作，t3,t4线程执行写操作
//        当t1,t2线程开启的时候，其他线程不开，即只执行都操作，是同时执行
//        当t1,t3线程开启的时候，即一个读一个写的时候，就要加锁异步执行了。(要么等读操作完成之后再写，要么等写操作完成之后再读)