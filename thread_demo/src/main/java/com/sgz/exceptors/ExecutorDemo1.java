package com.sgz.exceptors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程池框架：可缓存的线程池
 * @Auther: shigzh
 * @create 2019/8/11 16:07
 */
public class ExecutorDemo1 {
    public static void main(String[] args) {
        //可缓存的线程池，无限大小
        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        for(int i= 0; i < 10; i++){
            int temp = i;
            cachedThreadPool.execute(new Runnable() {//execute方法：启动线程，相当于start方法
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
        //关闭线程池
        cachedThreadPool.shutdown();

    }
}
