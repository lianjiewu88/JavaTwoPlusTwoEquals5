package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayTest {

	@SuppressWarnings("unused")
	private static void asListTest(){
		String[] a = {"A", "B"};
		List<String> list = Arrays.asList(a);
		System.out.println(list.getClass().getName()); // java.util.Arrays$ArrayList
		System.out.println(list.contains("A")); // true
		list.add("C");
		/*
		 * Jerry: 2016-01-15 9:09PM:
		 * Exception in thread "main" java.lang.UnsupportedOperationException
	at java.util.AbstractList.add(Unknown Source)
	at java.util.AbstractList.add(Unknown Source) 
	Arrays.asList() 会返回一个ArrayList，这是Arrays里内嵌的一个私有静态类，而并不是java.util.ArrayList类java.util.Arrays.ArrayList 有set(), get(), contains()方法，
	但并支持添加元素，所以大小是固定的，想要创建一个真正的ArrayList，你应该：
ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
		 */
	}
	
	private static void removeInLoop() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));

		/* ConcurrentModificationException
		for (String s : list) {
		    if (s.equals("a"))
		        list.remove(s);
		}*/
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
		    String s = iter.next();

		    if (s.equals("a")) {
		        iter.remove();
		    }
		}
		list.forEach(System.out::println);
	}
	public static void main(String[] args) {
	    // asListTest();
		removeInLoop();
		String[] a = {"A", "B"};
		// internal implementation: return new ArrayList<>(a);
		List<String> list1 = Arrays.asList(a);
		List<String> list2 = Arrays.asList(a);
		List<String> list3 = Arrays.asList(a);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list1.getClass().getCanonicalName());
	}
}
