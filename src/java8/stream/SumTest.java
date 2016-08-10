package java8.stream;

import java.util.Arrays;
import java.util.List;

class Age{
	private int age;
	public Age(int age){
		this.age = age;
	}
	
	public int getAgeValue(){
		return this.age;
	}
}
class User{
	private int id;
	private String name;
	private Age age;
	
	public User(int id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = new Age(age);
	}
	
	public Age getAge(){
		return this.age;
	}
	
	public int getAgeWrapper(){
		return this.age.getAgeValue();
	}
	
}
public class SumTest {

	private static List<User> users = Arrays.asList( 
            new User(1, "张三", 12),  
            new User(2, "李四", 21),  
            new User(3,"王五", 32),  
            new User(4, "赵六", 32));
	
	public static String covert2SnakeCase(final String camelCase)
	{
		final String regex = "([a-z])([A-Z])";
		final String replacement = "$1_$2";
		return camelCase.replaceAll(regex, replacement).toLowerCase();
	}
	
	public static void main(String[] args) {
		//double sum = users.parallelStream().mapToInt(User::getAge().getAgeValue()).reduce(0, (x, y) -> x + y); 
		double sum2 = users.parallelStream().mapToInt(User::getAgeWrapper).sum(); 
		//System.out.println("sum1: " + sum);
		System.out.println(" sum2: " + sum2);
		System.out.println(covert2SnakeCase("helloJerry"));

	}

}
