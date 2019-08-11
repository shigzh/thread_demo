package com.sgz.exceptors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程池框架：可固定长度的线程池
 * @Auther: shigzh
 * @create 2019/8/11 16:07
 */
public class ExecutorDemo2 {
    public static void main(String[] args) {
        //可固定长度的线程池，大小有限
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        for(int i= 0; i < 10; i++){
            int temp = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
        //关闭线程池
        fixedThreadPool.shutdown();
    }
}
