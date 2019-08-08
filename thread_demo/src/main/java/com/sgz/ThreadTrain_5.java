package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 多线程死锁
 * 产生原因：同步中嵌套同步,导致锁无法释放
 *
 * 分析：
 *      线程1获取锁的顺序：oj---》this--->oj（oj锁对象可以进行传递，没有进行释放）
 *      线程2获取锁的顺序：this---》oj
 *
 *      有可能线程1获取到oj锁对象，线程2获取到this对象这种情况
 *
 *       继续执行
 *
 *       此时：线程1想要往下执行需要this对象，但是这个时候this锁对象被线程2占用，
 *            线程2想要往下执行需要oj对象，但是这个时候oj锁对象被线程1占用
 *            两个线程互相占用，都不释放锁，所以造成死锁。
 *
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain_5 implements Runnable {
    private int trainCount = 100;
    private Object oj = new Object();
    public boolean flag = true;

    public void run() {

        if (flag) {
            while (trainCount > 0) {//线程t1就会一直走这里的方法
                synchronized (oj) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    sale();
                }
            }
        } else {
            while (trainCount > 0) {//线程t2就会一直走这里的方法
                sale();
            }

        }

    }

    public synchronized void sale() {//synchronized修饰方法，其锁对象为this，即当前调用该方法的对象
        synchronized (oj) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {

            }
            if (trainCount > 0) {
                System.out.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票");
                trainCount--;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadTrain_5 threadTrain = new ThreadTrain_5();
        Thread t1 = new Thread(threadTrain, "窗口1");
        Thread t2 = new Thread(threadTrain, "窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain.flag = false;
        t2.start();
    }

}
