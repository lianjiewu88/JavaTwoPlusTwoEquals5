package integerTest;

import java.util.Properties;

public class IntegerCompare {

	private static long start;
	private static void start(){
		start = System.currentTimeMillis();
	}
	
	public static void autoboxing(){
		Integer a = 10; 
		Integer b = Integer.valueOf(10);
	}
	private static long end(){
		return System.currentTimeMillis() - start;
	}
	
	private static final int NUM = 10000;
	private static int calc1(){
		Integer result = 0;
		for( Integer i = 0; i < NUM; i++){
			result += i;
		}
		return result;
	}
	
	private static int calc2(){
		int result = 0;
		for( int i = 0; i < NUM; i++){
			result += i;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Calc1: " + calc1());
		System.out.println("Calc2: " + calc2());
		
		start();
		for( int i = 0; i < NUM; i++){
			calc1();
		}
		System.out.println("Calc1 time: " + end());
		
		start();
		for( int i = 0; i < NUM; i++){
			calc2();
		}
		System.out.println("Calc2 time: " + end());
		
		Properties a = System.getProperties();
		
		Object ab = 2;
		Object b = 1;
	}
}
