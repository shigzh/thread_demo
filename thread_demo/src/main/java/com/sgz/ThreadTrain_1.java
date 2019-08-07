package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 使用synchronized修饰方法，此时充当锁的对象为调用同步方法的对象
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain_1 implements Runnable {
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
//    对象如同锁，持有锁的线程可以在同步中执行 
//    没持有锁的线程即使获取CPU的执行权，也进不去
    public synchronized void sale() {
        if (trainCount > 0) {
            System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "张票");
            trainCount--;
        }
    }

    public static void main(String[] args) {
        ThreadTrain_1 threadTrain = new ThreadTrain_1();
        Thread t1 = new Thread(threadTrain, "1号窗口");
        Thread t2 = new Thread(threadTrain, "2号窗口");
        t1.start();
        t2.start();
    }

}
