package quiz;

public class Pet79 {
	  public final String name;
	  public final String food;
	  public final String sound;
	  public Pet79(String name, String food, String sound) {
	      this.name = name; this.food = food; this.sound = sound; 
	  }

	public void eat(){
	     System.out.println(name + ": mmm, " + food);
	}

	public void play(){
	    System.out.println(name + ": " + sound);
	}

	public void sleep(){
	   System.out.println(name + ": zzzz...");
	}

	public void live(){
	    new Thread(){
	       public void run(){
	         while(true) {
	           eat();
	           play();
	           // The method sleep(long) in the type Thread is not applicable for the arguments ()
	           /* The method sleep(long) in the type Thread is not applicable for the arguments ()
编译器会再包含有正确名称的方法的最内层范围内查找需要的方法。对于sleep方法的调用，最内层的

范围就是包含有该调用的匿名类，这个类继承了Thread.sleep(long)方法，它们是该范围内唯一的名称

为sleep的方法，但是由于都带有参数，所以不适用。

从Thread那里继承到匿名类中的两个sleep方法shadow了我们代码里的方法。
Solution：在方法调用时适用受限的qualified this结构来显式地为该匿名类命名。
完美的solution是使用thread(runnable)构造器来替代对Thread的继承。这样匿名类就不会再继承

Thread.sleep方法。
 */
	           Pet79.this.sleep();
	      }
	   }
	 }.start();
	}

	public static void main(String[] args) {
		new Pet79("Fido", "beef", "Woof").live();

	}

}
