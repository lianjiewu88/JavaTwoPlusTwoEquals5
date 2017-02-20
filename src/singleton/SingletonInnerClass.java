package singleton;

public class SingletonInnerClass {
	
	/*
	 * 当执行getInstance()方法的时候就去调用FileIOHolder内部类里面的INSTANCE实例，
	 * 此时FileIOHolder内部类会被加载到内存里，在类加载的时候就对INSTANCE实例进行初始化。
	 * 和饿汉式一个道理，保证了只有一个实例，而且在调用getInstance()方法的时候才进行INSTANCE实例的初始化，
	 * 又具有懒汉式的部分特性。”
	 */
	private static final class FileIOHolder {
		private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
	}

	private SingletonInnerClass() {
		System.out.println("constructor of SingletonInnerClass...");
	}

	public static SingletonInnerClass getInstance() {
		System.out.println("GetInstance called...");
		String trimTest = new String("i042416").trim();
		return FileIOHolder.INSTANCE;
	}
	
	private static void objectTest(){
		Object obj = new Object();
		Class<? extends Object> clas = obj.getClass();
		System.out.println(clas.getClassLoader());
	}
	
	public static void main(String arg[]){
		Object obj1 = SingletonInnerClass.getInstance();
		Object obj2 = SingletonInnerClass.getInstance();
		System.out.println(obj1 == obj2);
		objectTest();
	}
}
