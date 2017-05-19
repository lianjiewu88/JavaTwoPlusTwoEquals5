package array;

public class compareScala {
	
	private static void test(){
		// Compiles but fails at runtime
		/*
		 * Exception in thread "main" java.lang.ArrayStoreException: java.lang.Object
	              at array.compareScala.test(compareScala.java:8)
	              at array.compareScala.main(compareScala.java:31)
		 */
		Object[] arrrrr = new String[1];
		// arrrrr[0] = new Object();
		 
		// This works
		Object[] arrrr2 = new Integer[1];
		arrrr2[0] = 1; // Autoboxing
		 
		// This doesn't work
		// Object[] arrrr3 = new int[];
		 
		// This works
		Object[] arr4[] = new Object[1][];
		 
		// So does this (initialisation):
		Object[][] arr5 = { { } };
		 
		// Or this (puzzle: Why does it work?):
		Object[][] arr6 = { { new int[1] } };
		 
		// But this doesn't work (assignment)
		// arr5 = { { } };

	}
	public static void main(String[] arg){
		test();
	}
}
