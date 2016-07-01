package reference;

import java.lang.ref.WeakReference;

class Person{
	private String mName;
	public Person(String name){
		this.mName = name;
	}
	public String getName(){
		return this.mName;
	}
}

public class WeakReferenceTest {

	public static void check(Person person){
		if( person == null){
			System.out.println("Reference invalid");
		}
		else {
			System.out.println("Reference still available");
		}
	}

	public static void hello(Person person){
		if( person == null)
			return;
		System.out.println("hello: " + person.getName());
	}
	public static void main(String[] args) {
		Person jerry = null;
		WeakReference<Person> person = new WeakReference<Person>(new Person("Jerry")); 
		jerry = new Person("Ben");
		Person restore = person.get();
		check(restore);
		
		System.gc();
		restore = person.get();
		check(restore);
		hello(restore);
	}

}
