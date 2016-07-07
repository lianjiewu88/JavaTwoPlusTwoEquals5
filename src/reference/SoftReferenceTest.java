package reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

class Person2{
	private String mName;
	public Person2(String name){
		this.mName = name;
	}
	public String getName(){
		return this.mName;
	}
	public void finalize(){
		System.out.println("finalize called: " + this.mName);
	}
	
	public String toString(){
		return "Hello, I am " + this.mName;
	}
}

public class SoftReferenceTest {

	public static void main(String[] args) {
		SoftReference<Person2> person = new SoftReference<Person2>(new Person2("Jerry")); 
		System.out.println(person.get());
		ArrayList<Person2> big = new ArrayList<Person2>();
		for( int i = 0; i < 10000; i++){
			big.add(new Person2(String.valueOf(i)));
		}
		System.gc();
		System.out.println("End: " + person.get());
	}
}
