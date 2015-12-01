package threadTest2;

class ThreadTest2 implements Runnable {

	@Override
	public void run() {
		System.out.println("before sleep");
          try {
             Thread.sleep(5000);
          } catch (InterruptedException e) {
                              System.out.println(Thread.currentThread().getName());
             Thread.currentThread().interrupt();
             System.out.println("after interrupt");
         }        
     System.out.println("after sleep");    
     
     try {
         Thread.sleep(5000);
     } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         //e.printStackTrace();
         System.out.println(Thread.currentThread().getName());
         Thread.currentThread().interrupt();
         System.out.println("after interrupt");
     }
     System.out.println("after sleep");    
     }
     
 }
 
public class threadTestJerry {
     
     public static void main(String[] args) {
         
         Thread t = new Thread(new ThreadTest2(), "thread-1");
     t.start();
     
     t.interrupt();
     System.out.println("is interruped? " + t.isInterrupted());
     }
 }