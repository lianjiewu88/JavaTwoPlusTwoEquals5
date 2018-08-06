package integerTest;

import java.lang.reflect.Field;

public class IntegerTest {

	private static void doSomethingMagic() throws Exception {
		Class cache = Integer.class.getDeclaredClasses()[0];
		Field c = cache.getDeclaredField("cache");
		c.setAccessible(true);
		Integer[] array = (Integer[]) c.get(cache);
		array[132] = array[133];
	}

	public static void main(String[] args) throws Exception {
		doSomethingMagic();
		System.out.printf("2 + 2 = %d", 2 + 2);
	}
}
