package consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class Consumer implements Runnable {

	BlockingQueue<String> queue;
	String id;
	private volatile boolean      isRunning               = true;
	public Consumer(BlockingQueue<String> queue, String id) {
        this.queue = queue;
        this.id = id;
    }
	
    public void stop() {
        isRunning = false;
    }
    
	@Override
	public void run() {
		System.out.println("Thread: " + id + " Consumer thread is running...");
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("Thread: " + id + " fetch data from linkedQueue..." + " queue size: " + queue.size());
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("Thread: " + id + " has consumed one data from queue: " + data
                    		+ "   Queue sise: " + queue.size());
                    // simulate data consumption
                    Thread.sleep(1000);
                } else {
                    isRunning = false;
                    System.out.println("Thread: " + id + " Consumer read queue timeout");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Thread: " + id + " consumer thread ends");
            
        }
	}

}