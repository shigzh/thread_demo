package com.sgz;
/**
 * @Description:Volatile 保证了线程间共享变量的及时可见性,当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。
 * 如果不使用volatile关键字修饰共享变量，已经将结果设置为fasle为什么？还一直在运行呢。
 * 原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。
 * 解决办法:使用Volatile关键字将解决线程之间可见性, 强制线程每次读取该值的时候都去“主内存”中取值
 *
 * volatile修饰的变量不会重排序
 *
 * 所以以后当我们在多线程中操作同一个共享变量的时候，最好都要使用volatile关键字修饰
 * @Auther:shigzh
 * @create: 2019/8/7 14:40
 */
class ThreadVolatileDemo extends Thread {
	public volatile boolean flag = true;//volatile修饰 保证了新值能立即同步到主内存
	//public  boolean flag = true;

	public void run() {
		System.out.println("开始执行子线程....");
		System.out.println("子线程中flag的值是："+flag);
		while (flag) {//如果不使用volatile关键字修饰,flag读取的是副本，没有及时读取到主内存结果
			//volatile 修饰的变量每次使用前立即从主内存刷新。
		}
		System.out.println("线程停止");
	}
	public void setRuning(boolean flag) {
		this.flag = flag;
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
		threadVolatileDemo.start();
		Thread.sleep(3000);
		threadVolatileDemo.setRuning(false);//这里操作只是主线程（main）里修改ThreadVolatileDemo里flag变量的值，只是在主线程本地内存中修改，并没有同步到主内存中，所以子线程中获取到的还是之前副本中的值
		System.out.println("flag 已经设置成false");
		Thread.sleep(1000);
		System.out.println(threadVolatileDemo.flag);

	}

}
