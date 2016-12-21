package thread;

class NotThreadSafe{
    StringBuilder builder = new StringBuilder();
 
    public void add(String text){
        this.builder.append(text);
    }   
    
    public void show(){
    	System.out.println("End: " + builder.toString());
    }
}

class MyRunnable implements Runnable{
	  NotThreadSafe instance = null;
	  String name = null;
	 
	  public MyRunnable(NotThreadSafe instance, String name){
	    this.instance = instance;
	    this.name = name;
	  }
	 
	  // sometimes t1, t2, sometimes t2,t1
	  public void run(){
	    this.instance.add("some text: " + this.name);
	  }
}
	  

public class NotThreadSafeTest {

	public static void main(String[] args) {
		NotThreadSafe sharedInstance = new NotThreadSafe();
		 
		Thread t1 = new Thread(new MyRunnable(sharedInstance, "t1"));
		t1.start();
		Thread t2 = new Thread(new MyRunnable(sharedInstance, "t2"));
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sharedInstance.show();
	}

}
