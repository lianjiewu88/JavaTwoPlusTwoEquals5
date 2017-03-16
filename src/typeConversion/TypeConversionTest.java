package typeConversion;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeConversionTest {

	private static void test( long a){
		System.out.println("a: " + a);
	}
	public static void main(String[] args) {
		int a = 1;
		long b = a;
		double c = 1;
		// Type mismatch: cannot convert from double to int
		// int d = c;
		test(a);
		
		RunTimeCheck(new HashMap<Integer,String>());
	}
	
	public static void RunTimeCheck(Object object){
		ArrayList<String> temp = new ArrayList<String>();
		
		temp = (ArrayList<String>) object;
	}

}
