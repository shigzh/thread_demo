package com.sgz.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 生产队列线程
 * @Auther: shigzh
 * @create 2019/8/11 10:26
 */
public class ProducerThread implements Runnable{

    private BlockingQueue<String> blockingQueue;
    private AtomicInteger count = new AtomicInteger();
    private volatile boolean flag = true;

    public ProducerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "生产者开始启动....");
        try {
            while (flag) {
                String data = count.incrementAndGet() + "";//相当于++1
                boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println(Thread.currentThread().getName() + ",生产队列" + data + "成功..");
                } else {
                    System.out.println(Thread.currentThread().getName() + ",生产队列" + data + "失败..");
                }
                Thread.sleep(1000);
            }
        }catch (Exception e){

        }finally {
            System.out.println(Thread.currentThread().getName() + ",生产者线程停止...");
        }
    }

    public void stop() {
        this.flag = false;
    }
}
