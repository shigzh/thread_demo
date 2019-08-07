package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 使用synchronized同步代码块，锁的粒度可以更细，并且充当锁的对象不一定是this，也可以是其它对象，所以使用起来更加灵活
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain_2 implements Runnable {
    private int trainCount = 100;

    private Object obj = new Object();
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
        synchronized(obj){//或者使用this
            if (trainCount > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "张票");
                trainCount--;
            }
        }
    }

    public static void main(String[] args) {
        ThreadTrain_2 threadTrain = new ThreadTrain_2();
        Thread t1 = new Thread(threadTrain, "1号窗口");
        Thread t2 = new Thread(threadTrain, "2号窗口");
        t1.start();
        t2.start();
    }

}
