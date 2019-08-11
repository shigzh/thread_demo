package com.sgz.exceptors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池框架：可定时执行的线程池
 * @Auther: shigzh
 * @create 2019/8/11 16:07
 */
public class ExecutorDemo3 {
    public static void main(String[] args) {
        //可定时执行的线程池，大小有限
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(3);
        for(int i= 0; i < 10; i++){
            int temp = i;
//            executorService.execute(new Runnable() {//没有定时效果
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+","+temp);
//                }
//            });
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            }, 3, TimeUnit.SECONDS);//相当于3秒后执行execute方法
        }
        //关闭线程池
        executorService.shutdown();
    }
}
