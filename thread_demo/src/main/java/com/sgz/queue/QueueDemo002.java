package com.sgz.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 阻塞式队列：存队列的时候如果满了就会等待，取队列的时候，如果获取不到（没有元素）也会等待
 * 队列遵循原则：先进先出，后进后出
 * @Auther: shigzh
 * @create 2019/8/11 9:01
 */
public class QueueDemo002 {
    public static void main(String[] args) throws InterruptedException {
        //阻塞式队列，只能存3个数据元素
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //往队列中存放元素
        boolean o1 =blockingQueue.offer("张三");
        System.out.println("o1："+o1);
        boolean o2 =blockingQueue.offer("李四",3, TimeUnit.SECONDS);//队列没有满后面两个参数没有意义，不会进行阻塞
        System.out.println("o2："+o2);
        boolean o3 =blockingQueue.offer("王五");
        System.out.println("o3："+o3);
        //offer: 只一个参数：非阻塞式添加元素，如果队列满了，不阻塞
        boolean o4 =blockingQueue.offer("赵六");
        System.out.println("o4："+o4);
        //offer: 3个参数：阻塞式添加元素，如果队列满了，阻塞着，阻塞时间是有后两个参数来指定，如果队列没有满后面两个参数没有意义，不会进行阻塞
        boolean o5 =blockingQueue.offer("孙七", 3, TimeUnit.SECONDS);
        System.out.println("o5："+o5);
        //获取队列，只能获取一个队列元素
        //poll():取出元素后直接从容器中删除了，执行完之后容器元素个数减1，如果没有元素了返回null
        System.out.println(blockingQueue.poll());//张三：
        //获取队列个数
        System.out.println(blockingQueue.size());//3
//        //获取队列，只能获取一个队列元素
//        System.out.println(blockingQueue.peek());//李四：取出元素后不会从容器中删除
//        //获取队列个数
//        System.out.println(blockingQueue.size());//2
//        //获取队列，只能获取一个队列元素
//        System.out.println(blockingQueue.peek());//获取结果还是李四：取出元素后不会从容器中删除
//        //获取队列个数
//        System.out.println(blockingQueue.size());//2

        System.out.println(blockingQueue.poll());//李四：
        //获取队列个数
        System.out.println(blockingQueue.size());//1
        System.out.println(blockingQueue.poll());//王五：
        //获取队列个数
        System.out.println(blockingQueue.size());//0
        //此时队列中已经没有元素了
        System.out.println(blockingQueue.poll());//null
        //获取队列个数
        System.out.println(blockingQueue.size());//0
        //此时队列中已经没有元素了,使用2个参数：会阻塞等着指定的时间
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));//null
        //获取队列个数
        System.out.println(blockingQueue.size());//0
    }
}
