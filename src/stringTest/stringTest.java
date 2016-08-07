package stringTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.zxing.common.StringUtils;

public class stringTest {

	/*
	 * 为什么安全敏感的字符串信息用char[]会比String对象更好？

　　String对象是不可变的就意味着直到垃圾回收器过来清扫之前它们都不会发生变化的。用数组的话，
	就可以很明确的修改它任何位置的字符元素。这样的话，如密码等安全敏感的信息就不会出现在系统的任何地方。

	 * 2016-01-16 10:28AM substring()方法具体是都干了些啥？

　　在JDK6中，这个方法只会在标识现有字符串的字符数组上 给一个窗口来表示结果字符串，但是不会创建一个新的字符串对象。
	如果需要创建个新字符串对象，可以这样在结果后面+一个空的字符串：
str.substring(m, n) + ""
　　这么写的话就会创建一个新的字符数组来表示结果字符串。同时，这么写也有一定的几率让你的代码跑的更快，
	因为垃圾回收器会吧没有在使用的大字符串回收而留下子字符串。
　　Oracle JDK7中的substring()方法会创建一个新的字符数组，而不用之前存在的。
	看看这张图就会明白substring()方法在JDK6和JDK7中的区别。
	 */
	public static void main(String[] args) {
		splitSpace("Jerry is a");
		convertDate("Sep 17, 2013");
		
		String[] a = {"1", "2"};
		String[] b = new String[]{"1","2"};
		
		System.out.println(a.equals(b));
		System.out.println(a);
		System.out.println(b);
		
		List<String> array = new ArrayList<String>(1);
		System.out.println("size: " + array.size());
		Boolean a1 = new Boolean(true);
		Boolean a2 = Boolean.valueOf(true);
		System.out.println("equal:? " + a1.equals(a2));
		return;
	}
	
	private native int aa();
	
	// String str = "Sep 17, 2013";
	private static void convertDate(String d) {
		Date date = null;
		try {
			date = new SimpleDateFormat("MMMM d, yy", Locale.ENGLISH).parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		//Tue Sep 17 00:00:00 EDT 2013
	}
	private static void splitSpace(String s) {
		String result[] = s.split("\\s+");
		for( int i = 0; i < result.length; i++)
			System.out.println("item: " + result[i]);
		
		String result2[] = s.split(" ");
		for( int i = 0; i < result2.length; i++)
			System.out.println("item: " + result2[i]);
	}
	
	private static void countTest(String whole, String part){
		// int n = StringUtils.countMatches(whole, part);
		// System.out.println(n);
	}
	
	public static String reverse(String s) {
	    int N = s.length();
	    char[] a = new char[N];
	    for (int i = 0; i < N; i++)
	       a[i] = s.charAt(N-i-1);
	    String reverse = new String(a);
	    System.out.println("after reverse: " + reverse);
	    return reverse;
	}
	
	

}
