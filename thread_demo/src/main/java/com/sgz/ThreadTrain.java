package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 什么都不做处理，会发生线程安全，数据错乱问题
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain implements Runnable {
    private int trainCount = 100;

    @Override
    public void run() {
        while (trainCount > 0) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }
            sale();
        }
    }

    public void sale() {
        if (trainCount > 0) {
            System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "张票");
            trainCount--;
        }
    }

    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread t1 = new Thread(threadTrain, "1号窗口");
        Thread t2 = new Thread(threadTrain, "2号窗口");
        t1.start();
        t2.start();
    }

}
