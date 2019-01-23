package odata;

public class TicketCreationTest {

	private final int THREAD_COUNT = 1;
	
	private void test() {
		Thread[] tasks = new Thread[THREAD_COUNT]; 
		for( int i = 0; i < THREAD_COUNT; i++ ){
			tasks[i] = new Thread( new TicketCreationRunner(), ThreadExecutionRecord.THREADPREFIX + i);
			ThreadExecutionRecord.insertRecord(ThreadExecutionRecord.THREADPREFIX + i);
			tasks[i].start();
		}
		try{
			for( int i = 0; i < THREAD_COUNT; i++ ){
				tasks[i].join();
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("all done");
		ThreadExecutionRecord.log();
	}

	public static void main(String[] args) {
		new TicketCreationTest().test();
	}

}
