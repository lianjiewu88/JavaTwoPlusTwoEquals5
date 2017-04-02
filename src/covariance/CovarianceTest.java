package covariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CovarianceTest {

	private static void arrayTest(){
		Number[] numbers = new Number[3];
		numbers[0] = new Integer(10);
		numbers[1] = new Double(3.14);
		numbers[2] = new Byte((byte) 1);
		for( int i = 0; i < numbers.length; i++){
			System.out.println(numbers[i]);
		}
	}
	
	private static void assignmentTest(){
		Integer[] myInts = {1,2,3,4};
		Number[] myNumber = myInts;
		for( int i = 0; i < myNumber.length; i++){
			System.out.println(myNumber[i]);
		}
		myNumber[0] = 3.14; 
		
	}
	
	private static long sum(Number[] numbers) {
		long summation = 0;
		for(Number number : numbers) {
			summation += number.longValue();
		}
		return	summation;
	}
	
	private static void sumTest(){
		Integer[] myInts = {1,2,3,4,5};
		Long[] myLongs = {1L, 2L, 3L, 4L, 5L};
		Double[] myDoubles = {1.0, 2.0, 3.0, 4.0, 5.0};
		System.out.println(sum(myInts));
		System.out.println(sum(myLongs));
		System.out.println(sum(myDoubles));
	}
	
	private static long sum2(List<Number> numbers) {
		long summation = 0;
		for(Number number : numbers) {
			summation += number.longValue();
		}
			return summation;
	}
	
	private static long sumTest2(){
		List<Integer> myInts = Arrays.asList(1,2,3,4,5);
		List<Long> myLongs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
		List<Double> myDoubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		System.out.println(sum2(myInts)); 
		System.out.println(sum2(myLongs)); 
		System.out.println(sum2(myDoubles)); 
	}
	
	private static long coVariantSum(List<? extends Number> list){
		// list.add(1);
		long sum = 0;
		for( int i = 0; i < list.size(); i++){
			sum += list.get(i).longValue();
		}
		return sum;
	}
	
	private static void coVariantTest(){
		List<Integer> myInts = Arrays.asList(1,2,3,4,5);
		List<Long> myLongs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
		List<Double> myDoubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		System.out.println(coVariantSum(myInts)); 
		System.out.println(coVariantSum(myLongs)); 
		System.out.println(coVariantSum(myDoubles)); 
	}
	
	abstract class Animal{
		protected String name;
		public Animal(String name){
			this.name = name;
		}
		abstract protected void shout();
	}
	
	class Dog extends Animal{

		public Dog(String name) {
			super(name);
		}

		@Override
		protected void shout() {
			System.out.println("Wang");
		}
	}
	
	class ToyDog extends Dog{

		public ToyDog(String name) {
			super(name);
		}

		@Override
		protected void shout() {
			System.out.println("...Wang...");
		}
	}
	
	class Cat extends Animal{

		public Cat(String name) {
			super(name);
		}
		@Override
		protected void shout() {
			System.out.println("Miao");
		}
	}
	
	private static void fillDogList(List<? super Dog> list){
		list.add(new Dog("Jerry"));
		list.add(new ToyDog("ToyJerry"));
		list.add(new Cat("i042416"));
	}
	public static void main(String[] args) {
		// sumTest();
		coVariantTest();	
		
		List<Number> list = new ArrayList<Number>();
		fillArrayList(list);
		for( int i = 0; i < list.size(); i++){
			System.out.println("index: " + i + " [super] element: " + list.get(i));
		}
	}
	
	
	

}
