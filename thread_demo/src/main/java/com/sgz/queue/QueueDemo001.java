package com.sgz.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: 非阻塞式队列最大好处，能够防止容器溢出，防止数据丢失
 * 队列遵循原则：先进先出，后进后出
 * @Auther: shigzh
 * @create 2019/8/11 9:01
 */
public class QueueDemo001 {
    public static void main(String[] args) {
        //非阻塞式队列，无界
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        //往队列中存放元素
        concurrentLinkedQueue.offer("张三");
        concurrentLinkedQueue.offer("李四");
        concurrentLinkedQueue.offer("王五");
        //获取队列，只能获取一个队列元素
        //poll():取出元素后直接从容器中删除了，执行完之后容器元素个数减1，如果没有元素了返回null
        System.out.println(concurrentLinkedQueue.poll());//张三：
        //获取队列个数
        System.out.println(concurrentLinkedQueue.size());//2
        //获取队列，只能获取一个队列元素
        System.out.println(concurrentLinkedQueue.peek());//李四：取出元素后不会从容器中删除
        //获取队列个数
        System.out.println(concurrentLinkedQueue.size());//2
        //获取队列，只能获取一个队列元素
        System.out.println(concurrentLinkedQueue.peek());//获取结果还是李四：取出元素后不会从容器中删除
        //获取队列个数
        System.out.println(concurrentLinkedQueue.size());//2
    }
}
