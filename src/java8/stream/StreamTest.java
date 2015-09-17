package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Person {
	
	private String _name;
	private int _age;
	public Person(String name, int age) {
		this._name = name;
		this._age = age;
	}
	
	public int getAge() {
		return _age;
	}
	
	public String getName() {
		return _name;
	}
}

public class StreamTest {

	private static void test(){
		
		List<Person> people = new ArrayList<Person>();

		people.add(new Person("Mohamed", 69));
		people.add(new Person("Doaa", 25));
		people.add(new Person("Malik", 6));
	
		Predicate<Person> pred = (p) -> p.getAge() > 65;
		displayPeople(people, pred);
	}
	
	private static void displayPeople(List<Person> people, Predicate<Person> pred) {
		System.out.println("Selected:");
		people.forEach(p -> {
			if (pred.test(p)) {
				System.out.println(p.getName());
			}
		});
		
		// another approach
		people.stream().filter(pred).forEach(p -> System.out.println("New approach: " + p.getName()));
	}

	private static void anotherApproach() {
		Person[] people = {
				new Person("Diablo", 69),
				new Person("Bale", 25),
				new Person("Mefesto", 6)};

		Stream<Person> stream = Stream.of(people);
		stream.forEach(p -> System.out.println("DiabloII: " + p.getName()));
		
		Stream<Person> stream2 = Arrays.stream(people);
		stream2.forEach(p -> System.out.println("Another approach: " + p.getName()));
	}
	
	public static void main(String[] args) {
		test();
		anotherApproach();
	}

}
