package com.sgz;

import java.util.concurrent.Semaphore;

/**
 * @Description: Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，
 * 超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，
 * 我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制
 *
 * 它的用法如下：
 * availablePermits函数用来获取当前可用的资源数量
 * wc.acquire(); //申请资源
 * wc.release();// 释放资源
 *
 * @Author: shigzh
 * @CreateDate: 2019/8/12 16:47
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//定义3个资源
        for (int i = 1; i <= 10; i++) {
            WcDemo wcDemo = new WcDemo("第" + i + "个人", semaphore);
            wcDemo.start();
        }
    }
}


class WcDemo extends Thread {
    private String name;
    private Semaphore semaphore;

    public WcDemo(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 剩下的资源
            int availablePermits = semaphore.availablePermits();
            if (availablePermits > 0) {
                System.out.println(name + "天助我也，终于有茅坑了.....");
            } else {
                System.out.println(name + "怎么没有茅坑了...");
            }
            // 申请资源
            semaphore.acquire();//如果没有资源了，在这里阻塞着
            System.out.println(name + "终于上厕所啦.爽啊" + ",剩下厕所:" + semaphore.availablePermits());
            Thread.sleep(2000);
            System.out.println(name + "厕所上完啦!");
        }catch (Exception e){

        }finally {
            // 释放资源
            semaphore.release();
        }

    }
}