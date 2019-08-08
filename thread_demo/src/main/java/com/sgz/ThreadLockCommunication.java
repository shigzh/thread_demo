package com.sgz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Description: 多线程之间通讯-lock
 *
 *  Lock lock  = new ReentrantLock();
 *  lock.lock();
 *  try{
 *        //可能会出现线程安全的操作
 *  }finally{
 *      //一定在finally中释放锁
 *   lock.ublock();
 *  }
 *
 * Condition的功能类似于在传统的线程技术中的,Object.wait()和Object.notify()的功能
 * Condition condition = lock.newCondition();
 *  condition.await();  类似wait
 *  condition.signal() 类似notify
 *
 *
 * <br/>@ Author: shigzh
 * <br/>@ CreateDate: 2019/8/8 11:32
 */
// 共享对象
class UserLock {
    public String userName;
    public String userSex;
    //线程通讯标识
    // 为true情况下 允许读，不能写
    // 为false情况下 允许写，不能读。
    public boolean flag = false;
    public Lock lock = new ReentrantLock();
}

// 生产这线程
class IntThradLock extends Thread {
    private UserLock user;
    private Condition condition ;

    public IntThradLock(UserLock user,Condition condition) {
        this.user = user;
        this.condition= condition;
    }

    public void run() {
        int count = 0;
        while (true) {
            try {
                user.lock.lock();
                if(user.flag){
                    condition.await();
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
                condition.signal();
            }catch (Exception e){

            }finally {
                user.lock.unlock();
            }

        }
    }
}

// 读取线程
class OutThreadLock extends Thread {
    private UserLock user;
    private Condition condition ;

    public OutThreadLock(UserLock user,Condition condition) {
        this.user = user;
        this.condition= condition;
    }

    public void run() {
        while (true) {
            try {
                user.lock.lock();
                if(!user.flag){
                    condition.await();//相当于wait()
                }
                System.out.println(user.userName + "--" + user.userSex);
                user.flag=false;//会刷新到主内存中
                // 唤醒当前线程
                condition.signal();//相当于notify()
            }catch (Exception e){

            }finally {
                user.lock.unlock();
            }

        }
    }
}

public class ThreadLockCommunication {
    public static void main(String[] args) {
        UserLock user = new UserLock();
        Condition condition = user.lock.newCondition();
        IntThradLock intThrad = new IntThradLock(user,condition);
        OutThreadLock outThread = new OutThreadLock(user,condition);
        intThrad.start();
        outThread.start();
    }
}

