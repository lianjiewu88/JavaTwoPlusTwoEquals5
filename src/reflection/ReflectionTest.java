package reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class ReflectionTest {

	private static void printClassMethod() {
		Class<?> c = null;
		try {
			c = Class.forName("java.lang.Boolean");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 这里的getConstructors()方法返回的是一个Constructor数组
		Constructor<?>[] cons = c.getConstructors();
		// 打印的方式你可以自己写，为了方便我用Arrays.toString()，凑合着看
		System.out.println(Arrays.toString(cons));
	}

	private static void constructNew() throws Exception {
		Class<?> c = null;
		try {
			c = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		char[] ch = { 'h', 'e', 'l', 'l', 'o' };
		String s = null;
		// 获得Class类对象的有参构造方法，括号里面参数的写法是：类型.class
		Constructor<?> con = c.getConstructor(char[].class);
		// 用此构造方法构造一个新的字符串对象，参数为一个char数组
		s = (String) con.newInstance(ch);
		System.out.println("构造的字符串：" + s);
	}

	public static void main(String[] args) throws Exception {
		constructNew();
		printClassMethod();
	}
}
