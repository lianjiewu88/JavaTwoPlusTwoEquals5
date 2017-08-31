package generic;

import java.util.ArrayList;
import java.util.List;

// http://stackoverflow.com/questions/5207115/java-generics-t-vs-object

public class GenericTest {

	public static final List<String> INDEX_FILE_NAMES = new ArrayList<String>() {

        {
            add("index.html");
            add("index.htm");
        }
    };
    
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
	
	public static void printList(List<Object> l) { 
		for (Object o : l) 
			System.out.println(o); 
	}
	
	public static void printList2(List<?> l) { 
		for (Object o : l) 
			System.out.println(o); 
	} 
	
	public static void main(String[] args) {
		
		GenericTest b = new GenericTest();
		System.out.println("Size: " + b.INDEX_FILE_NAMES.size());
		// HashMap<String, String> cookies = new HashMap<String, String>();
		foo2("Test");
		String a = "a";
		
		try {
			String result = GenericTest.requireNonNull(a);
			System.out.println(result);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		List<Integer> iList = new ArrayList<Integer>();
		iList.add(1);
		
		// The method printList(List<Object>) in the type GenericTest is not applicable 
		// for the arguments (List<Integer>)
		// printList(iList);
		// 上面代码中的问号是一个类型通配符。它读作“问号”。List<?>是任何泛型List的父类型，所以您完全可以将 List<Object>、
		// List<Integer>或 List<List<List<Flutzpah>>>传递给printList()。 
		printList2(iList);
	}

}
