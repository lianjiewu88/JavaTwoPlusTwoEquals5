package random;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*
 * 如果是一组选择好的随机数，那么事实上他们并不是真正随机的。随机数的算法是根据种子参数来进行计算的，（当前则是 -229985452 或者 -147909649）。每一次当申请一个随机数的时候，它会根据相同的随机数种子来生成一个相同的值 – 打印出”hello world”。
Random(-229985452).nextInt(27)
前六个随机数是：8，5，12，12，15，0.
Random(-147909649).nextInt(27)
前六个随机数是:23，15，18，12，4，0
当你将这些数字转换成字符的时候
104 –> h
101 –> e
108 –> l
108 –> l
111 –> o
119 –> w
111 –> o
114 –> r
108 –> l
100 –> d
你将获得hello world
 */
public class RandomTest {

	public void hello(){
		System.out.println("Hello");
	}
	public static String randomString(int i) {
		Random ran = new Random(i);
		StringBuilder sb = new StringBuilder();
		while (true) {
			
			int k = ran.nextInt(27);
			System.out.println("Jerry: " + k);
			if (k == 0)
				break;
			char c = (char)('`' + k);
			System.out.println("Number: " + k + " Char: " + c);
			sb.append((char) ('`' + k));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(randomString(-229985452) + " " + randomString(-147909649));
		
		// strangeTime();
		String clazz = "random.RandomTest";  
		try {
			Object o = Class.forName(clazz).newInstance();
			RandomTest r = (RandomTest)o;
			r.hello();
			Method m = o.getClass().getMethod("hello");
			m.invoke(o);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	private static void strangeTime() throws ParseException{

		    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    String str3 = "1927-12-31 23:54:07";  
		    String str4 = "1927-12-31 23:54:08";  
		    Date sDt3 = sf.parse(str3);  
		    Date sDt4 = sf.parse(str4);  
		    long ld3 = sDt3.getTime() /1000;  
		    long ld4 = sDt4.getTime() /1000;
		    System.out.println(ld4-ld3);
		}
	}

