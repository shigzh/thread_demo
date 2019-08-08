package com.sgz;

/**
 * @ Description: 多线程之间通讯
 * 注意：此时没有使用synchronized关键字同步，也没有使用lock；数据发生错乱，造成线程安全问题
 *
 * 需求：小军 男与小红 女 交替输出
 *
 * Wait、Notify一定要在synchronized里面进行使用，否则会报错
 *
 * .Synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。Synchronized的作用主要有：（1）确保线程互斥的访问同步代码（2）保证共享变量的修改在多线程之间能够及时可见
 *
 * <br/>@ Author: shigzh
 * <br/>@ CreateDate: 2019/8/8 11:32
 */
// 共享对象
class User {
    public String userName;
    public String userSex;
    //线程通讯标识
    // 为true情况下 允许读，不能写
    // 为false情况下 允许写，不能读。
    public boolean flag = false;
}
// 生产这线程
class IntThrad extends Thread {
    private User user;

    public IntThrad(User user) {
        this.user = user;
    }

    public void run() {
        int count = 0;
        while (true) {
            synchronized (user){
                if(user.flag){
                    try {
                        user.wait(); // 当前线程变为等待，但是可以释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    user.userName = "小军";
                    user.userSex = "男";
                } else {
                    user.userName = "小紅";
                    user.userSex = "女";
                }
                count = (count + 1) % 2;
                user.flag=true;//会刷新到主内存中
                // 唤醒当前线程
                user.notify();
            }
        }
    }
}
// 读取线程
class OutThread extends Thread {
    private User user;

    public OutThread(User user) {
        this.user = user;
    }

    public void run() {
        while (true) {
            synchronized (user){
                if(!user.flag){
                    try {
                        user.wait(); // 当前线程变为等待，但是可以释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(user.userName + "--" + user.userSex);
                user.flag=false;//会刷新到主内存中
                // 唤醒当前线程
                user.notify();
            }
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        User user = new User();
        IntThrad intThrad = new IntThrad(user);
        OutThread outThread = new OutThread(user);
        intThrad.start();
        outThread.start();
    }
}


// 在调用sleep()方法的过程中，线程不会释放对象锁,让出cpu给其他线程
// 而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备,获取对象锁进入运行状态。