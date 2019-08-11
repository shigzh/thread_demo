package com.sgz.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:
 * @Auther: shigzh
 * @create 2019/8/11 10:35
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
        ProducerThread producerThread = new ProducerThread(blockingQueue);
        ConsumerThread consumerThread = new ConsumerThread(blockingQueue);
        Thread t1 = new Thread(producerThread);
        Thread t2 = new Thread(consumerThread);
        t1.start();
        t2.start();
        //10秒后 停止线程..
        try {
            Thread.sleep(10*1000);
            producerThread.stop();//目的是不让再生产线程了
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
