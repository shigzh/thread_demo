package com.sgz;

/**
 * @Description: 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * @Auther:shigzh
 * @create: 2019/8/5 11:24
 */
public class JoinThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t1,i:" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    t1.join(); //先等t1线程执行完毕，t2线程再重新进入可运行状态
                } catch (Exception e) {
                    // TODO: handle exception
                }
                for (int i = 0; i < 20; i++) {
                    System.out.println("t2,i:" + i);
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    t2.join();//先等t2线程执行完毕，t3线程再重新进入可运行状态
                } catch (Exception e) {
                    // TODO: handle exception
                }
                for (int i = 0; i < 20; i++) {
                    System.out.println("t3,i:" + i);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
