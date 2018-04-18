package boxing;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;

public class boxingTest {
	
	private static Integer count;
	
	public static void main(String[] arg) {

		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		java.util.ArrayList<Integer> intList2 = new java.util.ArrayList<Integer>();
		
		intList2.add(2);

		intList.add(1); //autoboxing - primitive to object
		intList.add(2);//autoboxing

		ThreadLocal<Integer> intLocal = new ThreadLocal<Integer>();

		intLocal.set(4);//autoboxing
		int number = intList.get(0);// unboxing

		int local = intLocal.get();// unboxing in Java
		System.out.println(local);
		
		String a = "1" + "2" + "3";
		compare();
	}
	
	public static void compare() {
		//before autoboxing

		Integer iObject = Integer.valueOf(3);
		int iPrimitive = iObject.intValue();

		//after java5

		Integer iObject2 = 3;//autobxing - primitive to wrapper conversion
		int iPrimitive2 = iObject2;//unboxing - object to primitive conversion
		
		/* 另一个需要避免的问题就是混乱使用对象和原始数据值，一个具体的例子就是当我们在一个原始数据值与一个对象进行比较时，
		 * 如果这个对象没有进行初始化或者为Null，在自动拆箱过程中obj.xxxValue，会抛出NullPointerException,如下面的代码
		 */
		
		//NullPointerException on unboxing

		if( count <= 0) {
		    System.out.println("Count is not started yet");
		}

	}
}
