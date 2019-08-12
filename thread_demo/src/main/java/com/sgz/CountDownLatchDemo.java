package com.sgz;

import java.util.concurrent.CountDownLatch;

/**
 * @ Description: CountDownLatch是通过一个计数器来实现的，计数器的初始值可以设置为为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，
 * 然后在闭锁上等待的线程就可以恢复执行任务
 * <br/>@ Author: shigzh
 * <br/>@ CreateDate: 2019/8/12 15:32
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",子线程开始执行...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",子线程结束执行...");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",子线程开始执行...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",子线程结束执行...");
                countDownLatch.countDown();//计数器值每次减去1
            }
        }).start();
        countDownLatch.await();// 減去为0,恢复任务继续执行
        System.out.println("两个子线程执行完毕....");
        System.out.println("主线程继续执行.....");
        for (int i = 0; i <10; i++) {
            System.out.println("main,i:"+i);
        }
    }
}
