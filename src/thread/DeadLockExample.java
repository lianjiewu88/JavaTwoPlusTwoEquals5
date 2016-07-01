package thread;

public class DeadLockExample {

	/*
	 * Thread 1: locked resource 1
	   Thread 2: locked resource 2
	 */
	public static void main(String[] args) {
		final String resource1 = "ABAP";
		final String resource2 = "Java";
		// t1 tries to lock resource1 then resource2
		Thread t1 = new Thread() {
			public void run() {
				synchronized (resource1) {
					System.out.println("Thread 1: locked resource 1");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					synchronized (resource2) {
						System.out.println("Thread 1: locked resource 2");
					}
				}
			}
		};

		Thread t2 = new Thread() {
			public void run() {
				synchronized (resource2) {
					System.out.println("Thread 2: locked resource 2");

					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					synchronized (resource1) {
						System.out.println("Thread 2: locked resource 1");
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}
