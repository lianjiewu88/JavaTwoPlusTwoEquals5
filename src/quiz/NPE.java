package quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class NPE {

	public static<E> List<E> withoutDuplicated(List<E> original) {
	     // return new ArrayList<E>(new LinkedHashSet<E>(original));
		LinkedHashSet<E> temp = new LinkedHashSet<E>(original);
		return new ArrayList<E>(temp);
	}
	
	public static String[] parse(String string) {
	    // return string.split(", \\S*"); // does not work
		// return string.split(","); // work
		return string.split(",\\S*"); // work
	}
	
	public static void main(String[] args) {
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		// Boolean b = (map != null ? map.get("test") : false);
		Boolean b = (map != null ? map.get("test") : Boolean.FALSE);
		System.out.println(b);
		
		ArrayList<String> input = new ArrayList<String>();
		input.add("Jerry");
		input.add("Jerry");
		input.add("ABAP");
		
		List<String> result = withoutDuplicated(input);
		result.forEach(entry -> System.out.println("result: " + entry));
		
		String input2 = "ABAP2, Scala222, JavaScript2"; // result ABAP2,Scala222,JavaScript2
		String[] result2 = parse(input2);
		System.out.println("length: " + result2.length);
		List<String> convert = Arrays.asList(result2);
		convert.forEach(entry -> System.out.println("result2: " + entry));
		
		 int[][][] b2 = {
                 {
                          {1,2,3},
                          {1,2,3}
                 },
                 {
                          {3,4,1},
                          {2,3,4}
                 }
        };
		 
		final int COUNT = 3;
		// String[COUNT][COUNT][COUNT] mul1;
		// String[COUNT][COUNT][] mul = new String[COUNT][COUNT][];
		String[][][] correct;
		correct = new String[COUNT][][];
		for( int i = 0; i < COUNT; i++){
			correct[i] = new String[COUNT][];
			for( int j = 0; j < COUNT; j++){
				correct[i][j] = new String[COUNT];
				for( int k = 0; k < COUNT; k++){
					correct[i][j][k] = "" + i + "-" + j + "-" + k ;
				}
			}
		}
		System.out.println("deep: " + Arrays.deepToString(correct));
		
		Integer i = 4;
		System.out.println("Byte: " + i.byteValue()); // 4
		//移位就算出了一个数字的二进制原码中有几个1
		System.out.println("count: " + Integer.bitCount(4));
		Calendar cal = Calendar.getInstance();
		cal.set(1999, Calendar.DECEMBER, 31);
		System.out.println(cal.get(Calendar.YEAR) + " " );
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}

}
