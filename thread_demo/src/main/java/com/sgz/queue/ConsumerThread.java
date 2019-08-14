package com.sgz.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 消费者队列
 * 这里return会跳出循环
 * @Auther: shigzh
 * @create 2019/8/11 10:31
 */
class ConsumerThread implements Runnable {
    private volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue;

    public ConsumerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "消费者开始启动....");
        try {
            while (flag) {
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (data == null) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() +",消费者超过2秒时间未获取到消息.");
                    return;//这里return会跳出循环
                }
                System.out.println(Thread.currentThread().getName() +",消费者获取到队列信息成功,data:" + data);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("消费者已经停止...");
        }
    }

}