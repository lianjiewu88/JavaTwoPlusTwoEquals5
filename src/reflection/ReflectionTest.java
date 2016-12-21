package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;


class Person {   
    public String name;   
    private int age;   

    public Person(String name, int age) {   
        this.name = name;   
        this.age = age;   
    }   
}  

public class ReflectionTest {

	private static void changePrivate() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Person p = new Person("zhangsan",12);   

        Class<?> c = p.getClass();   

        //获取公共属性的值   
        Field f1 = c.getField("name");   
        //get(p)表明要获取是哪个对象的值   
        String str = (String) f1.get(p);   
        System.out.println("姓名： " + str);   

        //获取私有属性的值   
        Field f2 = c.getDeclaredField("age");   
        //age是私有属性，所以要设置安全检查为true  
        /*
         * Jerry 2016-12-21 9:45AM - if commented out - Class reflection.ReflectionTest can not access a member of class 
         * reflection.Person with modifiers "private"
         */
        // f2.setAccessible(true);   
        int age = (int) f2.get(p);   
        System.out.println("年龄： " + age);   
	}
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
		changePrivate();
	}
}
