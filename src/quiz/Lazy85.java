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
		     int a = -1;
		     int b = -(2);
		     // int类型的负数常量都是由正数十进制字面常量前加一元负操作符-构成。
		     int c = Integer.MAX_VALUE;
		     int d = -c;
		     int e = -2147483648;
		     // int f = -(2147483648); // literal out of range
		     System.out.println("status: " + initialized);
		     /* 等价关系：自反，传递，对称。
				==不是自反。Double.NaN == Double.NaN不成立。
				
				等价关系：自反，传递，对称。
==不是自反。Double.NaN == Double.NaN不成立。

当比较两个原始类型数值时，==首先进行二进制数据类型提升 - binary numeric promotion, 会导致

两个数值中有一个会进行拓宽原始类型转换 - widening primitive conversion. 将int或者long转换

成float，或者long转换成double时，会导致精度丢失。

实现不可传递性：

选择两个较大的但不相同的long型数值赋给x和z，将一个与前面两个long型数值相近的double型数值赋

给y。==作用于原始类型时具有不可传递性。

		     */
		     long x = Long.MAX_VALUE;
		     double y = (double)Long.MAX_VALUE;
		     long z = Long.MAX_VALUE - 1;
		     System.out.println( ( x==y) + ""); // true
		     System.out.println( ( y==z) + ""); // true
		     System.out.println( ( x==z) + ""); // false
		   } catch(InterruptedException e){
		     throw new AssertionError(e);
		      }
	}

	
}
