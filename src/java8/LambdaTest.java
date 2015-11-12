package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {

	/* (params) -> expression
		(params) -> statement
		(params) -> { statements } */
	
private static void startThread() {
		new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!,thread id: " + 
				Thread.currentThread().getId()) ).start();
	}
	
	private static void printThreadId(String name) {
		System.out.println("Current Thread id:" + Thread.currentThread().getId() + " - " + name);
	}
	
	private static void listOperation() {
		printThreadId("listOperation()");
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println("ListOperation: " + n));
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		System.out.println("after....");
		features.forEach(System.out::println);
	}
	
	private static void filter(List<String> names, Predicate<String> condition) {
		for(String name: names) {
			if(condition.test(name)) {
				System.out.println(name + "....Filter passed()");
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
		List<String> features = Arrays.asList("StarLambdas", "Star Default Method", "Stream API", "Date and Time API");
		features.forEach(System.out::println);
	}
	
	// 为每个订单加上12%的税
	private static void map() {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		// costBeforeTax.stream().map((cost) -> cost + 2*cost).forEach(System.out::println);
		costBeforeTax.stream().map( (cost) -> {
			// in debugger cost is Integer
			System.out.println("Instance of? : " + cost instanceof Object);
			System.out.println(cost);
			System.out.println("Hello there");
			
			return (int)cost + (int)cost * .12f;
			
		}
		).forEach(System.out::println);
	}
	
	private static void reduceTest() {
		// specify concrete type here!!!
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

		double bill = (double) costBeforeTax.stream().map( (cost) -> { 
			return (int)cost + (int)cost * .12f;
		}).reduce((a, b) -> a + b).get();
		System.out.println("Total : " + bill);
	}
	
	private static void concatenate() {
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}
	
	public static void main(String[] args) {
		startThread();
		listOperation();
		System.out.println("Start FilterTest.............");
		filterTest();
		map();
		reduceTest();
		concatenate();
	}

}
