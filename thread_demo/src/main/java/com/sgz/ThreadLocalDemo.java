package com.sgz;

/**
 * @Description: ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * @Auther: shigzh
 * @create: 2019/8/7 16:48
 */
public class ThreadLocalDemo extends Thread{
    private Res res;

    public ThreadLocalDemo(Res res) {
        this.res = res;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res.getNum());
        }

    }

    public static void main(String[] args) {
        Res res = new Res();
        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo2 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo3 = new ThreadLocalDemo(res);
        threadLocalDemo1.start();
        threadLocalDemo2.start();
        threadLocalDemo3.start();
    }
}
