package atomicIntegerTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestAtomic {
	
	 public TestAtomic(){
		 
	 }
	  private static final int MAX_THREADS = 3;
	  private static final int TASK_COUNT = 3;
	  private static final int TARGET_COUNT = 100 * 10000;
	  private AtomicInteger acount = new AtomicInteger(0);
	  private int count = 0;
	  synchronized int inc() {    
	      return ++count;
	  }
	  synchronized int getCount() {    
	      return count;
	  }

	class SyncThread implements Runnable {
	    String name;    
	    long startTime;
	    TestAtomic out;    
	    public SyncThread(TestAtomic o, long startTime) {        		     
	        this.out = o;        
	        this.startTime = startTime;
	    }    
	    @Override
	    public void run() {        
	      int v = out.inc();        
	      while (v < TARGET_COUNT) {
	            v = out.inc();
	      }        
	      long endTime = System.currentTimeMillis();
	      System.out.println("SyncThread spend:" + (endTime - startTime) + "ms" + ", v=" + v);
	    }
	}

	class AtomicThread implements Runnable {
	    String name;    
	    long startTime;    
	    public AtomicThread(long startTime) {        
	       this.startTime = startTime;
	    }    
	    @Override
	    public void run() {        
	       int v = acount.incrementAndGet();        
	       while (v < TARGET_COUNT) {
	            v = acount.incrementAndGet();
	        }        
	        long endTime = System.currentTimeMillis();
	        System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms" + ", v=" + v);
	    }
	}

	@Test
	public void testSync() throws InterruptedException {
	    ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);    
	    long startTime = System.currentTimeMillis();
	    SyncThread sync = new SyncThread(this, startTime);    
	    for (int i = 0; i < TASK_COUNT; i++) {
	        exe.submit(sync);
	    }
	    Thread.sleep(3000);
	}

	@Test
	public void testAtomic() throws InterruptedException {
	    ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);    
	    long startTime = System.currentTimeMillis();
	    AtomicThread atomic = new AtomicThread(startTime);    
	    for (int i = 0; i < TASK_COUNT; i++) {
	        exe.submit(atomic);
	    }
	    Thread.sleep(3000);
	}
	}

