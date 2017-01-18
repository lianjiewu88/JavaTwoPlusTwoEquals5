package threadLocalTest;

import threadLocalTest.TestNum.TestClient;

class TestNum {

	// 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
	// multiple thread can share with this value, but each thread has its dedicated value
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			return 0;
		}
	}; // 获取下一个序列值

	public int getNextNum() {

		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	static class TestClient extends Thread {
		private TestNum sn;

		public TestClient(TestNum sn) {
			this.sn = sn;
		}

		public void run() {
			for (int i = 0; i < 3; i++) {
				// 每个线程打出3个序列值
				System.out.println("thread[" + Thread.currentThread().getName()
						+ "] --> sn[" + sn.getNextNum() + "]");
			}
		}
	}
}

public class LocalTest{
	public static void main(String[] args) {
		TestNum sn = new TestNum(); // 3个线程共享sn，各自产生序列号
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		t1.start();
		t2.start();
		t3.start();
	}
}
