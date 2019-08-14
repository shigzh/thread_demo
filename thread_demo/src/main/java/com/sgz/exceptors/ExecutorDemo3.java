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
//            executorService.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+","+temp);
//                }
//            }, 3, TimeUnit.SECONDS);//相当于3秒后执行execute方法

//            executorService.scheduleWithFixedDelay(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+","+temp);
//                }
//            },3,1,TimeUnit.SECONDS);//3秒后开始执行，以后每1秒执行一次
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            },3,1,TimeUnit.SECONDS);//3秒后开始执行，以后每1秒执行一次
        }

        //关闭线程池
        //executorService.shutdown();
    }
}
//scheduleAtFixedRate 方法，顾名思义，它的方法名称的意思是：已固定的频率来执行某项计划(任务)。
//scheduleWithFixedDealy,相对固定的延迟后，执行某项计划。
//第一个方法是固定的频率来执行某项计划，它不受计划执行时间的影响。时间，它就执行。
// 而第二个方法，相对固定，是相对任务的。即无论某个任务执行多长时间，等执行完了，我再延迟指定的时间。也就是第二个方法，它受计划执行时间的影响。
