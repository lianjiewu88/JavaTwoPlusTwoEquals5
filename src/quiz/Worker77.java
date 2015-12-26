package quiz;

import java.util.Timer;
import java.util.TimerTask;

/*
 * start new worker thread in Main thread, id: 1
I am in worker thread: 10
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
called by good boss, quittingTime is true, thread id: 1
TimerTask run is called, thread id: 11
Evil boss set the quittingTime to false, thread id: 11
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...
I am in worker thread, quittingTime is false, so pretend to work...

Thread.join在正在被join的那个thread实例上调用Object.wait方法，这样就在等待期间释放了该对象上

的锁。于是Evil的定时器线程能够将quittingTime重新设置成false。
WorkerThread类的作者使用了实例上的锁来确保quit和keepWorking方法的互斥。但是这种用法与超类内部对该锁的用法发生了冲突。
除非有关于某个类的详细说明，否则不要假设库中的这个类对它的实例或者类上的锁会或者不会做某些事

情。对于库的任何调用都可能会产生对wait，notify或者其他同步化方法的调用。

如果你的类扩展了库中的某个类，而这个库中的类可能使用了它的锁，则不要使用与这个类或者它的实例

自动关联的那些锁。应该在自己累的一个私有field中创建一个单独的锁对象。

 */
public class Worker77 extends Thread {
	   private volatile boolean quittingTime = false;
	   private final Object lock = new Object();
	   public void run() {
		   System.out.println("I am in worker thread: " + Thread.currentThread().getId());
	      while(!quittingTime) {
	    	 System.out.println("I am in worker thread, quittingTime is false, so pretend to work...");  
	         pretendToWork();
	      }
	      System.out.println("Beer is good");
	   }

	   private void pretendToWork() {
	      try {
	          Thread.sleep(300);
	      } catch ( InterruptedException ex) {}
	 }

	// called by good boss
	   /*
	synchronized void quit() throws InterruptedException {
		// executed in main thread
		System.out.println("called by good boss, quittingTime is true, thread id: " + Thread.currentThread().getId());
	     quittingTime = true;
	     join();
	}*/
	   void quit() throws InterruptedException {
		     synchronized ( lock ) {
		      System.out.println("called by good boss, quittingTime is true, thread id: " + Thread.currentThread().getId());
		       quittingTime = true;
		       join();
		  }
		}
	   

	// called by evil boss
	   /*
	synchronized void keepWorking() {
		// will be called in timerThread: 11
		System.out.println("Evil boss set the quittingTime to false, thread id: " + Thread.currentThread().getId());
	    quittingTime = false;
	}*/
	   void keepWorking() {
		    synchronized(lock) {
		       quittingTime = false;
		    }
		}
	   
	   /* 另一种solution是让Worker类实现Runnable而不是扩展Thread，然后在创建每个工人线程时都使用Thread

(Runnable)构造器。这样可以将每个worker实例上的锁与其线程上的锁解耦。 */

	public static void main(String[] args ) throws InterruptedException {
	      final Worker77 worker = new Worker77();
	      System.out.println("start new worker thread in Main thread, id: " + Thread.currentThread().getId());
	      worker.start();
	      Timer t = new Timer(true); // Daemon Thread
	      t.schedule( new TimerTask() {
	        public void run() { 
	        	System.out.println("TimerTask run is called, thread id: " + Thread.currentThread().getId());
	        	worker.keepWorking(); 
	        }
	     }, 500);
	     Thread.sleep(1400);
	     worker.quit();
	  }
	}