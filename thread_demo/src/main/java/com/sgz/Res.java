package com.sgz;

/**
 * @Description:
 * ThreadLocal实现原理
 *      ThreadLocal通过map集合
 *      Map.put(“当前线程”,值)；
 *
 *     每个ThreadLocal变量只存一个值，不像map那样，可以存多个值
 *
 * @Auther:shigzh
 * @create: 2019/8/7 16:54
 */
public class Res {
    // 生成序列号共享变量
    public static Integer count = 0;
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
           // System.out.println("initialValue方法执行了。。。");
            return 0;
        };

    };

    public static Integer getNum() {
      //  System.out.println("要调用threadLocal.get()之前。。。。");
        Integer count = threadLocal.get() + 1;
      //  System.out.println("要调用threadLocal.get()之后。。。。");
        threadLocal.set(count);
        return count;
    }
}


//ThreadLocal的接口方法
//        ThreadLocal类接口很简单，只有4个方法：
//        •	void set(Object value)设置当前线程的线程局部变量的值。
//        •	public Object get()该方法返回当前线程所对应的线程局部变量。
//        •	public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
//        •	protected Object initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。