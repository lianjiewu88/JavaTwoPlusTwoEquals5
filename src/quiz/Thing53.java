package quiz;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;


class Thing{
    public Thing(int i ){ }
}

class Null54 {
	  public static void hello(){
	      System.out.println("hello");
	  }
	}

// Alternate constructor invocation - Private Constructor Capture
public class Thing53 extends Thing{

	private final int arg;
	public Thing53(){
		// Cannot refer to an instance field arg while explicitly invoking a constructor
	  // super(arg = 1);
		this(1); // 如果用Thing53(1)就有编译错误。
	}
	
    private Thing53(int i){
    	super(i);
    	arg = i;
    }
	public static void main(String[] args) {
		//The static method hello() from the type Null54 should be accessed in a static way
		
		/* 在静态方法调用中，适用表达式作为其限定符不是好主意。表达式的值所引用的对象的运行期类型在确

定哪一个方法被调用时不起任何作用。静态方法调用的限定表达式是可以计算的，但是值将被忽略，没

有任何要求其值为空的限制。
*/
		((Null54)null).hello();
		// Java不允许一个本地变量声明语句作为一条语句在for,while或do 循环中重复执行。只能直接出现在一

		// 个语句块中。
		AtomicLong numCreated = new AtomicLong();
		for( int i = 0; i < 5; i++){
			numCreated.incrementAndGet();
			String a = new String("a");
			System.out.println("value: " + numCreated.get());
		}
		// or 
		for( int i = 0; i < 5; i++)
			new String("a");
		BigInteger fivek = new BigInteger("5000");
		BigInteger total = fivek.add(new BigInteger("500"));
		System.out.println(total.intValue());
		
		// int vals[] = {789,678,567,456,345,234,123,012};
		 int vals[] = {789,678,567,456,345,234,123,12};

		Set diffs = new HashSet();

		for( int i = 0; i < vals.length; i++)
		   for( int j = i; j < vals.length; j++) 
		      diffs.add(vals[i] - vals[j]);
		System.out.println(diffs);
		
		Thread.currentThread().interrupt();
		if( Thread.interrupted()){ // 返回true，清空了thread的中断状态
		   System.out.println("Status:" + Thread.interrupted()); // 总是会清除thread当前的中断状态
		}
		

	}

}
