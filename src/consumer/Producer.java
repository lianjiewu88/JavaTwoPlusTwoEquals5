package consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
 
	BlockingQueue<String> queue;
	String id;
    public Producer(BlockingQueue<String> queue, String id) {
        this.queue = queue;
        this.id = id;
    }
 
    @Override
    public void run() {
        String data = null;
        
        try {
            while (isRunning) {
                System.out.println("PRODUCER: " + id + " is running");
                Thread.sleep(100);
 
                data = "data:" + count.incrementAndGet();
                System.out.println("Thread: " + id + " procedued data into queue: " + data + " ...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("failed to put data into queue: " + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Thread: " + id + " quit from producer thread");
        }
    }
 
    public void stop() {
        isRunning = false;
    }
 
    private volatile boolean      isRunning               = true;
    private static AtomicInteger  count                   = new AtomicInteger();
 
}