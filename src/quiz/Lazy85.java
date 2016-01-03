package quiz;

class Lazy85NotWork {
	  private static boolean initialized = false;
	  static {
	      Thread t = new Thread(new Runnable(){
	           public void run(){
	              initialized = true;
	          } 
	       });
	   t.start();
	   try{
		  /* 
		   * 当一个线程访问一个类的某个成员时，它会检查这个类是否已经被初始化。有4种可能：
1. 该类尚未被初始化
2. 该类正在被当前线程初始化，这厮对初始化的递归请求。
3. 该类正在被其他线程而不是当前线程初始化
4. 该类已经被初始化

后台线程执行run方法，将initialized 设置为true之前，回去检查Lazy类是否已经被初始化。这个时

候，这个类正在被另外一个线程初始化，即情况3， 于是当前线程，即后台工作线程会等待class对象

直至初始化完成。那个正在进行初始化工作的线程，即主线程，正在等待着后台线程运行结束。死锁。
		   */
	     t.join();
	   } catch(InterruptedException e){
	     throw new AssertionError(e);
	      }
	}
	  
	public static void main(String[] args) {
		System.out.println(initialized);
	}
}

public class Lazy85{
	private static boolean initialized = false;
	// System.out.println("1"); syntax error
	private static Thread t = new Thread( new Runnable() {
	     public void run(){
	    	 System.out.println(" in backend thread, set initialized to true");
	         initialized = true;
	     }
	 });

	static { 
		System.out.println(" in static code block, start backend thread");
	  t.start();
	}
	/*
	 * 最好的办法就是不要在类进行初始化时启动任何后台线程。或者让主线程再等待后台线程之前就完成类

		的初始化。
	 */
	static public void main(String[] arg) {
		try{
			System.out.println(" in main thread, call t.join()");
		     t.join();
		     System.out.println("status: " + initialized);
		   } catch(InterruptedException e){
		     throw new AssertionError(e);
		      }
	}

	
}
