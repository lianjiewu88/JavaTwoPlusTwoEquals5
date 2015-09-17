package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Lambda {

	/* (params) -> expression
		(params) -> statement
		(params) -> { statements } */
	
private static void startThread() {
		new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
	}
	
	private static void listOperation() {
		List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println(n));
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		System.out.println("after....");
		features.forEach(System.out::println);
	}
	
	private static void filter(List<String> names, Predicate condition) {
		for(String name: names) {
			if(condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}
	
	private static void filterTest() {
		
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		
		System.out.println("<<<<<<<<<Languages which starts with J :");
		filter(languages, (str)->((String) str).startsWith("J"));
		
		System.out.println("<<<<<<<<<Languages which ends with a ");
		filter(languages, (str)->((String) str).endsWith("a"));
		
		System.out.println("<<<<<<<<<Print all languages :");
		filter(languages, (str)->true);
		
		System.out.println("<<<<<<<<<<Print no language : ");
		filter(languages, (str)->false);
		
		System.out.println("<<<<<<<<<<Print language whose length greater than 4:");
		filter(languages, (str)->((String) str).length() > 4);
		
		
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		languages.stream().filter(startsWithJ.and(fourLetterLong))
		.forEach((n) -> System.out.println("Most Powerful ! nName, which starts with 'J' and four letter long is : " + n));
		
		// Method reference, if the argument is directly output
		List features = Arrays.asList("StarLambdas", "Star Default Method", "Stream API", "Date and Time API");
		features.forEach(System.out::println);
	}
	
	// 为每个订单加上12%的税
	private static void map() {
		List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		// costBeforeTax.stream().map((cost) -> cost + 2*cost).forEach(System.out::println);
		costBeforeTax.stream().map( (cost) -> {
			System.out.println("Hello there");
			
			return 2 + (int)cost * 1.1f;
			
		}
		).forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		startThread();
		listOperation();
		System.out.println("Start FilterTest.............");
		filterTest();
		map();
	}

}
