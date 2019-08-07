package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 同步方法使用的是什么锁？
 * 证明方式: 一个线程使用同步代码块(this明锁),另一个线程使用同步函数。如果两个线程抢票不能实现同步，那么会出现数据错误。
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain_3 implements Runnable {
    private int trainCount = 100;
    private Object oj = new Object();
    public boolean flag = true;

    public void run() {
        if (flag) {
            while (trainCount > 0) {
                synchronized (this) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if (trainCount > 0) {
                        System.out.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票========》》》同步代码块执行");
                        trainCount--;
                    }
                }

            }
        } else {
            while (trainCount > 0) {
                sale();
            }

        }

    }

    public synchronized void sale() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (trainCount > 0) {
            System.out.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票========》》》同步方法执行");
            trainCount--;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain_3 threadTrain = new ThreadTrain_3();
        Thread t1 = new Thread(threadTrain, "窗口1");
        Thread t2 = new Thread(threadTrain, "窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain.flag = false;
        t2.start();

    }
}