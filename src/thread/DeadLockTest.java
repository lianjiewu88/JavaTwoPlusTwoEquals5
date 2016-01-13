package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class ProducerConsumer {
	  private String value = "";
	  private boolean hasValue = false;
	  public void produce(String value) {
	    while (hasValue) {
	      try {
	        Thread.sleep(500);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    System.out.println("Thread: " + Thread.currentThread().getId() + " Producing " + value + " as the next consumable");
	    this.value = value;
	    hasValue = true;
	  }
	  public String consume() {
	    while (!hasValue) {
	      try {
	        Thread.sleep(500);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    String value = this.value;
	    hasValue = false;
	    System.out.println("Current Thread: " + Thread.currentThread().getId() + " Consumed " + value);
	    return value;
	  }
	}

class UnsafeCounter {
	  private volatile int counter;
	  synchronized public void inc() {
	    counter++;
	  }
	  synchronized public void dec() {
	    counter--;
	  }
	  synchronized public int get() {
	    return counter;
	  }
	}

class AtomicCounter {
	  private AtomicInteger atomicInteger = new AtomicInteger();
	  public void inc() {
	    atomicInteger.incrementAndGet();
	  }
	  public void dec() {
	    atomicInteger.decrementAndGet();
	  }
	  public int get() {
	    return atomicInteger.intValue();
	  }
	}

public class DeadLockTest {
	
	public void testUnsafeCounter() throws InterruptedException {
	    UnsafeCounter unsafeCounter = new UnsafeCounter();
	    int NUM = 5000;
	    Thread first = new Thread(() -> {
	      for (int i = 0; i < NUM; i++) { 
	        unsafeCounter.inc();
	      }
	    });
	    Thread second = new Thread(() -> {
	      for (int i = 0; i < NUM; i++) {
	        unsafeCounter.dec();
	      }
	    });
	    first.start();
	    second.start();
	    first.join();
	    second.join();
	    /*
	     * 一个线程增加计数器，另一个线程将计数器减少同样次数。运行这个测试，期望的结果是计数器的值为 0，但这无法得到保证。
	     * 大部分时候是 0，但有的时候是 -1, -2, 1, 2 等，
	     * 任何位于[-5, 5]之间的整数都有可能。
	     */
	    System.out.println("Current counter value: " + unsafeCounter.get());
	  }
	

	public static void main(String[] arg) throws InterruptedException {
		DeadLockTest instance = new DeadLockTest();
		// instance.testProduceConsume();
		instance.testUnsafeCounter();
	}
	
	public void testProduceConsume() throws InterruptedException {
	    ProducerConsumer producerConsumer = new ProducerConsumer();
	    List<String>values = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
	    Thread writerThread = new Thread(() -> values.stream()
	        .forEach(producerConsumer::produce));
	    Thread readerThread = new Thread(() -> {
	      for (int i = 0; i < values.size(); i++) {
	        producerConsumer.consume();
	      }
	    });
	    writerThread.start();
	    readerThread.start();
	    writerThread.join();
	    readerThread.join();
	  }
}

