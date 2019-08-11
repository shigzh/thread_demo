package com.sgz.exceptors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程池框架：只产生一个的线程池
 * @Auther: shigzh
 * @create 2019/8/11 16:07
 */
public class ExecutorDemo4 {
    public static void main(String[] args) {
        //只产生一个的线程池
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        for(int i= 0; i < 10; i++){
            int temp = i;
            executorService.execute(new Runnable() {//execute方法：启动线程，相当于start方法
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
        //关闭线程池
        executorService.shutdown();
    }
}
