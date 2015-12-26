package quiz;

// pong ping
/*在一个同步化的静态方法执行之前，他会获取与它的Class对象相关联的一个管程锁。主线程会再创建第二

个线程之前获得与PingPong.class相关联的那个锁。只要主线程占有着这个锁，第二个线程就不可能执行

同步化的静态方法。在main方法打印了Ping并且执行结束之后，第二个线程才能执行p只有当主线程放弃那

个锁时，第二个线程才被允许获得这个锁并打印Pong。 
一个线程可以重复地获得某个相同的锁。当run方法调用pong方法时，主线程就被允许再次获得与

PingPong.class相关联的锁。
*/

public class PingPong76 {
	  public static synchronized void main(String[] a) {
	      Thread t = new Thread() {
	         public void run() { pong(); }
	  };
	  t.run();
	  System.out.println("Ping");
	}

	static synchronized void pong(){
	    System.out.println("Pong");
	 }
	}