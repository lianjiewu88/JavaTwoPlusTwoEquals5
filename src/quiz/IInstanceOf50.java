package quiz;

class Type2{
}

class Type3{
}

public class IInstanceOf50 {

	public static void main(String[] args) {
		String s = null;
		// 如果s为null，一律返回false
		System.out.println( s instanceof String);
		// Type2必须是String的子类，否则编译错误
		// System.out.println(new Type2() instanceof String);
		/* runtime error: 
		 * Exception in thread "main" java.lang.ClassCastException: java.lang.Object cannot be cast to quiz.Type3
		 */
		Type3 t3 = (Type3) new Object();
	}

}
