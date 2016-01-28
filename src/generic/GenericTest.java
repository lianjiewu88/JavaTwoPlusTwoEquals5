package generic;

// http://stackoverflow.com/questions/5207115/java-generics-t-vs-object

public class GenericTest {

	public static <T> T requireNonNull(T obj) {
		  if (obj == null) {
		    throw new NullPointerException();
		  } else {
		    return obj;
		  }
		}
	
	public static <T> void foo2(Object x) { 
		System.out.println("Generic: " + x); 
	} 
	
	public static <T> void foo3(T x) { 
		System.out.println("Generic: " + x); 
	} 
	
	/* syntax error: T is not a type
	public static void foo4(T x) { 
		System.out.println("Generic: " + x); 
	} */
	
	public static void main(String[] args) {
		
		foo2("Test");
		String a = "a";
		
		try {
		String result = GenericTest.requireNonNull(a);
		System.out.println(result);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		

	}

}
