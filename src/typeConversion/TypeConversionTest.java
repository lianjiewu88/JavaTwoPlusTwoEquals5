package typeConversion;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeConversionTest {

	private static void test( int a){
		System.out.println("a: " + a);
	}
	public static void main(String[] args) {
		long a = 1l;
		// test(a);
		
		RunTimeCheck(new HashMap<Integer,String>());
	}
	
	public static void RunTimeCheck(Object object){
		ArrayList<String> temp = new ArrayList<String>();
		
		temp = (ArrayList<String>) object;
	}

}
