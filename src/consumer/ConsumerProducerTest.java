package consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsumerProducerTest {
 
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(15);
 
        Producer producer1 = new Producer(queue, "PROD1");
        Producer producer2 = new Producer(queue, "PROD2");
        Producer producer3 = new Producer(queue, "PROD3");
        Consumer consumer1 = new Consumer(queue, "CONSUMER1");
        Consumer consumer2 = new Consumer(queue, "CONSUMER2");
 
        ExecutorService service = Executors.newCachedThreadPool();
        
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
 
        Thread.sleep(3 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        consumer1.stop();
        consumer2.stop();
 
        Thread.sleep(2000);

        service.shutdown();
    }
}