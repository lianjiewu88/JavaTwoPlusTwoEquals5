package generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Test<I extends Integer> {
    <L extends Long> void x(I i, L l) {
        System.out.println(i.intValue() + ", " +  l.longValue());
    }
    
    static <T extends Number> void PrintA(List<T> l) {
        for(Number n: l) {
            System.out.println(n);
        }
    }
}


class Test2<T extends Serializable & Cloneable> {
	Test2<Date> d = null;
	
	static public <T extends Runnable & Serializable> void execute(T t) {
		t.run();
	}
}

class Test3 {
	static
	  {
	    System.out.println("static");
	  }
}

/* The generic type parameter T that you’re binding to instances of the classTest must implement both Serializable and Cloneable. For instance,String is not a possible bound, but Date is:
Bound mismatch: The type String is not a valid substitute for the bounded parameter <T extends Serializable & 
 Cloneable> of the type Test2<T>
*/
// Doesn't compile
// Test2<String> s = null;
 
// Compiles



public class TypeAlias {
	public static void main(String[] arg){
		Test<Integer> jerry = new Test<Integer>();
		// The method x(Integer, L) in the type Test<Integer> is not applicable for the arguments (int, int)
		// jerry.x(2, 4);
		jerry.x(3,2l); // ok - 无需显式定义类型，直接放一个变量进去，compiler自行handle
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		
		ArrayList<String> b = new ArrayList<String>();
		b.add("1");
		b.add("2");
	
		Test.PrintA(a);
		// Test3 c; // will not trigger static
		new Test3(); // will trigger
		// The method PrintA(List<T>) in the type Test is not applicable for the arguments (ArrayList<String>)
		//Test.PrintA(b); Jerry 2016-02-04 15:42PM 我甚至不需要显式再定义 T了。
		
		/*
		 * The method execute(T) in the type Test2 is not applicable for the arguments (Serializable)
	- The target type of this expression must be a functional interface
		 */
		// Test2.execute((Serializable) (() -> {}));
		Test2.execute((Runnable & Serializable) (() -> 
		{ System.out.println("I am Jerry");})
		);
		
	}
}
