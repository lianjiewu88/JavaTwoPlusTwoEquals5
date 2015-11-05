package pattern;

public class SingleInstance{
	  private static SingleInstance sInstance = new SingleInstance();
	  private SingleInstance(){}
	  public static SingleInstance getInstance(){
	     return sInstance;
	  }
	  public static void main(String[] arg) {
		  System.out.println(SingleInstance.getInstance());
		  System.out.println(SingleInstance.getInstance());
		  System.out.println(SingleInstance2.getInstance());
		  System.out.println(SingleInstance2.getInstance());
		  System.out.println(SingleInstance3.getInstance());
		  System.out.println(SingleInstance3.getInstance());
		  System.out.println(SingleInstance4.getInstance());
		  System.out.println(SingleInstance4.getInstance());
		  System.out.println(SingleInstance5.getInstance());
		  System.out.println(SingleInstance5.getInstance());
	  }
}

class SingleInstance2 {
	  private static SingleInstance2 sInstance;
	  private SingleInstance2(){}
	  public static SingleInstance2 getInstance(){
	    if( null == sInstance){
	        sInstance = new SingleInstance2();
	    }
	    return sInstance;
	  }
	  /* 上述的代码在多个线程密集调用getInstance时，存在创建多个实例的可能。
	   * 比如线程A进入null == sInstance这段代码块，而在A线程未创建完成实例时，
	   * 如果线程B也进入了该代码块，必然会造成两个实例的产生。
	   * 
	   */
}

class SingleInstance3 {
    private static SingleInstance3 sInstance;
    private SingleInstance3() {}
    public static synchronized SingleInstance3 getInstance(){
      if (null == sInstance){
         sInstance = new SingleInstance3();
      }
    return sInstance;
    }
}
/* Volatile是轻量级的synchronized，它在多处理器开发中保证了共享变量的“可见性”。
 * 可见性的意思是当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值。
 * 使用volatile修饰sInstance变量之后，可以确保多个线程之间正确处理sInstance变量。
 * 
 */
class SingleInstance4 {
	   private static volatile SingleInstance4 sInstance;
	   private SingleInstance4(){}
	   public static SingleInstance4 getInstance(){
	      if(null==sInstance){
	          synchronized(SingleInstance4.class){
	            if(null == sInstance){
	               sInstance = new SingleInstance4();
	            }
	          }
	      }
	   return sInstance;
	   }
	}

/* 在Java中，类的静态初始化会在类被加载时触发，我们利用这个原理，可以实现利用这一特性，
 * 结合内部类，可以实现如下的代码，进行懒汉式创建实例。
 * 
 */
class SingleInstance5 {
	  private SingleInstance5(){}
	  public static SingleInstance5 getInstance() {
	    return SingleInstanceHolder.sInstance;
	  }
	  private static class SingleInstanceHolder {
	    private static SingleInstance5 sInstance = new SingleInstance5();
	  }
	}
