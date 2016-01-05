package threadTest2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	private static final int COUNT = 100;

	// 每一个线程减一次1
	private static class TestRunnable implements Runnable {
		private final CountDownLatch countDownLatch;

		TestRunnable(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			System.out.println("Thread id: " + Thread.currentThread().getId());
			countDownLatch.countDown();
			System.out.println("Left number: " + countDownLatch.getCount());
			/* 
			 * Left number: 0
!!!!!!!!!! Game over!!!!!!!!!!!
Left number: 32
!!!!!!!!!! Game over!!!!!!!!!!!
testThreadPool:34
Left number: 4
!!!!!!!!!! Game over!!!!!!!!!!!

			 */
			if( countDownLatch.getCount() == 0){
				System.out.println("!!!!!!!!!! Game over!!!!!!!!!!!");
			}
		}
	}

	public void testThreadPool() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(COUNT);
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		long bg = System.currentTimeMillis();
		for (int i = 0; i < COUNT; i++) {
			Runnable command = new TestRunnable(countDownLatch);
			executorService.execute(command);
		}
		countDownLatch.await();
		System.out.println("testThreadPool:"
				+ (System.currentTimeMillis() - bg));
	}

	public void testNewThread() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(COUNT);
		long bg = System.currentTimeMillis();
		for (int i = 0; i < COUNT; i++) {
			Runnable command = new TestRunnable(countDownLatch);
			Thread thread = new Thread(command);
			thread.start();
		}
		countDownLatch.await();
		System.out
				.println("testNewThread:" + (System.currentTimeMillis() - bg));
	}

	public static void main(String[] arg) {
		ThreadPoolTest a = new ThreadPoolTest();
		try {
			a.testThreadPool();
			// a.testNewThread();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
