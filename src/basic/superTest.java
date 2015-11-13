package basic;

import java.util.ArrayList;
import java.util.List;

public class superTest {

	private static List<? extends Number> getListforRead() {
		List<Number> foo1 = new ArrayList<Number>();
		foo1.add(new Integer(1));
		foo1.add(new Double(1.2));
		foo1.add(new Float(1.3f));
		return foo1;
	}
	
	private static List<? super Integer> getListforWrite() {
		List<Number> foo1 = new ArrayList<Number>();
		foo1.add(new Integer(1));
		foo1.add(new Double(1.2));
		foo1.add(new Float(1.3f));
		return foo1;
	}
	
	public static void main(String[] args) {

		List<? extends Number> foo1 = new ArrayList<Number>();  // Number "extends" Number (in this context)
		List<? extends Number> foo2 = new ArrayList<Integer>(); // Integer extends Number
		List<? extends Number> foo3 = new ArrayList<Double>();  // Double extends Number
		
		List<Integer> foo4 = new ArrayList<Integer>();  
		foo4.add(1);
		
		/* Error!
		 * foo1.add(1, new Integer(1));
		 */
		
		/*
		Reading - Given the above possible assignments, what type of object are you guarenteed to read from List foo3:

			1. You can read a Number because any of the lists that could be assigned to foo3 
			contain a Number or a subclass of Number.
			2. You CANNOT read an Integer because foo3 could be pointing at a List<Double>.
			3. You CANNOT read a Double because foo3 could be pointing at a List<Integer>. */
		
		List<? extends Number> list = getListforRead();
		// Jerry 2015-11-13 11:31AM - you cannot insert anything on list, write protection available!
		
		list.forEach(t->System.out.println("Write enabled: " + t));
		
		/*	
		Writing - Given the above possible assignments, what type of object could you add to List foo3 that would be legal for all the above possible ArrayList assignments:

			You CANNOT add an Integer because foo3 could be pointing at a List<Double>.
			You CANNOT add a Double because foo3 could be pointing at a List<Integer>.
			You CANNOT add a Number because foo3 could be pointing at a List<Integer>.
		*/
		
		/* SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER SUPER  */
		List<? super Integer> foo5 = new ArrayList<Integer>();  // Integer is a "superclass" of Integer (in this context)
		List<? super Integer> foo6 = new ArrayList<Number>();   // Number is a superclass of Integer
		List<? super Integer> foo7 = new ArrayList<Object>();   // Object is a superclass of Integer
		
		List<? super Integer> listWrite = getListforWrite();
		/*  You can add an Integer because an Integer is allowed in any of above lists.
			You can add an instance of a subclass of Integer because an instance of a subclass of Integer 
			is allowed in any of the above lists.
			You CANNOT  add a Double because foo3 could be pointing at a ArrayList<Integer>.
			You CANNOT add a Number because foo3 could be pointing at a ArrayList<Integer>.
			You CANNOT add a Object because foo3 could be pointing at a ArrayList<Integer>.
		 */
		listWrite.add(1);
		// listWrite.add(new Double(1.2));
		
		/*Reading - Given the above possible assignments, what type of object are you guaranteed to receive when you read from List foo3:

		You aren't guaranteed an Integer because foo3 could be pointing at a List<Number> or List<Object>.
		You aren't guaranteed an Number because foo3 could be pointing at a List<Object>.
		The only guarantee is that you will get an instance of an Object or subclass of Object (but you don't know what subclass).
		 */
		for( int i = 0; i < listWrite.size(); i++) {
			System.out.println(listWrite.get(i));
		}
		/*
		 * PECS

		Remember PECS: "Producer Extends, Consumer Super".

		"Producer Extends" - If you need a List to produce T values (you want to read 
		Ts from the list), you need to declare it with ? extends T, e.g. List<? extends Integer>. 
		But you cannot add to this list.

		"Consumer Super" - If you need a List to consume T values (you want to write Ts 
		into the list), you need to declare it with ? super T, e.g. List<? super Integer>. 
		
		But there are no guarantees what type of object you may read from this list.

		If you need to both read from and write to a list, you need to declare it exactly with no wildcards, e.g. List<Integer>.
		 */
		
	}

}
