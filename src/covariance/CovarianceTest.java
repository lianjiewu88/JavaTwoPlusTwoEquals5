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
	
	private static void fillDogList(List<? super ToyDog> dogList) {
		dogList.add(new ToyDog("Jerry"));
		
		ToyDog dog = (ToyDog) dogList.get(0);
	}
	
	private static void superTest(){
		List<ToyDog> toyDog = new ArrayList<ToyDog>();
		List<Dog> dog = new ArrayList<Dog>();
		List<Animal> animal = new ArrayList<Animal>();
		List<Object> object = new ArrayList<Object>();
		List<Cat> cat = new ArrayList<Cat>();
		
		fillDogList(toyDog);
		fillDogList(dog);
		fillDogList(animal);
		fillDogList(object);
		// fillDogList(cat);
	}
	
	private static void copyTest(){
		List<ToyDog> src = new ArrayList<ToyDog>();
		src.add(new ToyDog("Jerry1"));
		src.add(new ToyDog("Jerry2"));
		
		List<Cat> dest = new ArrayList(Arrays.asList( new Object[src.size()])); 
		java.util.Collections.copy(dest, src);
		for( int i = 0; i < dest.size(); i++){
			dest.get(i).shout();
		}
	}
	public static void main(String[] args) {
		// sumTest();
		// coVariantTest();	
		// copyTest();
		// superTest();
		List<Dog> dog = new ArrayList<Dog>();
		
		forABAP(dog);
		
		List<ToyDog> toyDog = new ArrayList<ToyDog>();
		
		forABAP(toyDog);
		
		List<Cat> cat = new ArrayList<Cat>();
		
		forABAP(cat);
	}
	
	private static void forABAPer(List<? extends Dog> list){
		Dog dog = list.get(0);
	}
}
