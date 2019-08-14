package com.sgz;
/**
 * 可参照博客：https://www.cnblogs.com/kubidemanong/p/9505944.html
 *
 * @Description:Volatile 保证了线程间共享变量的及时可见性,当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。
 * 如果不使用volatile关键字修饰共享变量，已经将结果设置为fasle为什么？还一直在运行呢。
 * 原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。
 * 解决办法:使用Volatile关键字将解决线程之间可见性, 强制线程每次读取该值的时候都去“主内存”中取值
 *
 * volatile修饰的变量不会重排序
 *   不过这里需要注意的是，虚拟机只是保证这个变量之前的代码一定比它先执行，但并没有保证这个变量之前的代码不可以重排序。之后的也一样。
 *
 * volatile不具有原子性
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

//
//原子操作
//		原子操作：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
//		也就是说，处理器要嘛把这组操作全部执行完，中间不允许被其他操作所打断，要嘛这组操作不要执行。
//		刚才说Java里面的运行并非是原子操作。我举个例子，例如这句代码
//
//		int a = b + 1;
//		处理器在处理代码的时候，需要处理以下三个操作：
//
//		从内存中读取b的值。
//		进行a = b + 1这个运算
//		把a的值写回到内存中
//		而这三个操作处理器是不一定就会连续执行的，有可能执行了第一个操作之后，处理器就跑去执行别的操作的。


//
//      为什么保证了可见性而不能线程安全。
//
//		场景：
//
//		volatile修饰的x
//
//		多线程进行x++
//
//		x++是非原子操作分三个步骤：
//
//		(1)读取x的值
//
//		(2)计算x值+1
//
//		(3)写入x的值到内存
//
//		假设：线程一执行完（2）算出了值后被阻塞
//
//		线程二执行完三个操作后将结果写回内存，由于线程一已经算出结果不会再去读取x的值，没有计算之前是立即从主存中读取的
//
//		所以线程一唤醒后接着写入，导致并发失败。volatile不具有原子性
