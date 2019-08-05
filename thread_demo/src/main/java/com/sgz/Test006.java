package com.sgz;

//join
public class Test006 {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("子线程,i:" + i);
				}
			}
		});
		t1.start();
		// 当前线程释放cpu执行资格权，等t1执行完毕之后，才会继续进行执行。
		t1.join();
		for (int i = 0; i < 10; i++) {
			System.out.println("main线程,i:" + i);
		}
	}

}

//Yield告诉当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程，当前线程有可能会再次抢到cpu执行权继续执行(即有可能下次执行的还是该线程)
//使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。cpu会从众多的可执行态里选择，
//		也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的，并不是说一定会执行其他线程而该线程在下一次中不会执行到了。

//线程实例的方法join()方法可以使得一个线程在另一个线程结束后再执行。如果join()方法在一个线程实例上调用，当前运行着的线程将阻塞直到这个线程实例完成了执行。

//		比如在B线程中执行线程A的join方法，a.join(),那么B线程就会暂停执行，先等A线程执行完毕，B线程再重新进入可运行状态。
//
//		join方法中还可以设置时间a.join(1000),这样就让A执行规定时间。
//
//		在A线程执行过程中，如果还有其他线程，其他线程不受影响，会和A线程一起进行，知道A 线程执行结束，B线程重新加入。