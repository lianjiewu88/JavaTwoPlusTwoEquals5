package quiz;

import java.sql.SQLException;
// http://blog.jooq.org/2013/10/17/add-some-entropy-to-your-jvm/

/*
 * Exception in thread "main" java.sql.SQLException
	at quiz.ExceptionTest.main(ExceptionTest.java:8)
 */
/*
 * cannot compile
class Test {
    Object x() { return "abc"; }
    String x() { return "123"; }
    But wait a second. Check out the Javadoc of Class.getMethod(String, Class...). It reads:
Note that there may be more than one matching method in a class because while the Java language forbids a class to declare multiple methods with the same signature but different return types, the Java virtual machine does not. This increased flexibility in the virtual machine can be used to implement various language features. For example, covariant returns can be implemented with bridge methods; the bridge method and the method being overridden would have the same signature but different return types.

}
*/

abstract class Parent23J<T> {
    abstract T x();
}
 
class Child extends Parent23J<String> {
    @Override
    String x() { return "abc"; }
}

// All of these are two-dimensional arrays!
class TerribleArray {
    int[][] a = new int[0][]; 
    int[] b [] = new int[0][]; 
    int c [][] = new int[0][]; 
    int[][] d = {{}};
    int[] e[] = {{}};
    int f[][] = {{}};
}


public class ExceptionTest {
    // No throws clause here, however exception can really be thrown
    public static void main(String[] args) {
    	/*
    	 * The type Child is not generic; it cannot be parameterized with arguments <String>
    	 */
    	// Child<String> a = new Child<String>();
    	Child a = new Child();
    	System.out.println(a.x());
    	// TerribleArray ab = new TerribleArray();
    	
    	// testCondition();
    	addTest();
        doThrow(new SQLException());
    }
  
    static void doThrow(Exception e) {
    	ExceptionTest.<RuntimeException> doThrow0(e);
    }
  
    /*
     * A compound assignment expression of the form E1 op= E2 is equivalent to E1 = (T)((E1) op (E2)), 
     * where T is the type of E1, except that E1 is evaluated only once.
     */
    private static void addTest() {
    	byte b = 10;
    	b *= 5.7;
    	System.out.println(b); // prints 57
    	
    	char ch = '0';
    	ch *= 1.1;
    	System.out.println(ch); // prints '4'
    	
    	char ch2 = 'A';
    	ch2 *= 1.5;
    	System.out.println(ch2); // prints 'a'

    }
    private static void  testCondition(){
    	Object o1 = true ? new Integer(1) : new Double(2.0);
    	Object o2;
    	if (true)
    	    o2 = new Integer(1);
    	else
    	    o2 = new Double(2.0);
    	System.out.println(o1); // 1.0
    	System.out.println(o2); // 1
    	
    	Integer i = new Integer(1);
    	if (i.equals(1))
    	    i = null;
    	Double d = new Double(2.0);
    	Object o = true ? i : d; // NullPointerException!
    	System.out.println(o);
    }
    @SuppressWarnings("unchecked")
    static <E extends Exception> 
    void doThrow0(Exception e) throws E {
        throw (E) e;
    }

}
