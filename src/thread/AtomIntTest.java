package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomIntTest {
	private AtomicInteger atomicI = new AtomicInteger(0);

	private int i = 0;
	
	public static void main(String[] args) {
		final AtomIntTest cas = new AtomIntTest();
		final int TOTAL_THREAD_NUM = 200;
		List<Thread> ts = new ArrayList<Thread>();

		long start = System.currentTimeMillis();
		for (int j = 0; j < TOTAL_THREAD_NUM; j++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					// System.out.println(" I am thread: " + Thread.currentThread().getId());
					for (int i = 0; i < 1000; i++) {
						cas.count(); // i++
						cas.safeCount();
					}
				}
			});
			ts.add(t);
		}
		System.out.println("total thread number: " + ts.size());

		for (Thread t : ts) { // start 600 threads
			t.start();
		}

		// 等待所有线程执行完成
		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("unsafe: " + cas.i);
		System.out.println("safe: " + cas.atomicI.get());
		// System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 
	 * 使用CAS实现线程安全计数器
	 */
	private void safeCount() {
		for (;;) {
			int i = atomicI.get();
			boolean suc = atomicI.compareAndSet(i, ++i);
			if (suc) {
				break;
			}
		}
	}

	/**
	 * 
	 * 非线程安全计数器
	 */

	synchronized private void count() {
		i++;
	}
}
