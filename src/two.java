import java.lang.reflect.Field;

public class two {
	
	// touch .gitignore
	public static void getCurrentName() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
		
	}
	
	public static void main(String[] args) throws Exception {
		getCurrentName();
		Class cache = Integer.class.getDeclaredClasses()[0];
		Field c = cache.getDeclaredField("cache");
		c.setAccessible(true);
		Integer[] array = (Integer[]) c.get(cache);
		array[132] = array[133];
		System.out.printf("%d",2 + 2);
	}
}