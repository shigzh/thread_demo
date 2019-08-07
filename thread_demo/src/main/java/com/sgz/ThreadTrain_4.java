package com.sgz;

/**
 * @Description:需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果
 * 静态同步函数: 锁对象可以用 getClass方法获取(对象.getClass())，也可以用当前  类名.class 表示。
 * synchronized 修饰方法使用锁是当前this锁
 * synchronized 修饰静态方法使用锁是当前类的字节码文件
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
public class ThreadTrain_4 implements Runnable {
    private static int trainCount = 100;

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

    public static void sale() {
        synchronized(ThreadTrain_4.class){
            if (trainCount > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "张票");
                trainCount--;
            }
        }
    }

    public static void main(String[] args) {
        ThreadTrain_4 threadTrain = new ThreadTrain_4();
        Thread t1 = new Thread(threadTrain, "1号窗口");
        Thread t2 = new Thread(threadTrain, "2号窗口");
        t1.start();
        t2.start();
    }

}
