package java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {

	class Human {
	    private String name;
	    private int age;

	    public String getName() {
	    	return name;
	    }
	    
	    public Human() {
	    	
	    }
	    public int getAge() {
	    	return age;
	    }
	    
	    public Human(final String name, final int age) {
	        this.name = name;
	        this.age = age;
	    }
	}
	/* Jerry 2015-11-12 : should put this into instance method 
	 * This is what Lists.newArrayList does:

@GwtCompatible(serializable = true)
public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<E>();
}
So these two are basically the same, with using newArrayList having the advantage on not 
having to duplicate the generic type. This is very helpful on complex generics:

List<Map<X,List<Y>> list = new ArrayList<Map<X,List<Y>>();

List<Map<X,List<Y>> list = Lists.newArrayList();

No enclosing instance of type Sorter is accessible. Must qualify the allocation with an enclosing instance 
of type Sorter ( e.g X.new A() where x is an instance of Sorter )

*/
	
	public void sortTest(){
		List<Human> humans = new ArrayList<Human>();

		humans.add(new Human("Sarah", 10));
		humans.add(new Human("Wack", 13));
		humans.add(new Human("Jack", 12));
		
		Collections.sort(humans, new Comparator<Human>() {
			@Override
			public int compare(Human h1, Human h2) {
				return h1.getName().compareTo(h2.getName());
			}
		});
		
		System.out.println("list....");
		humans.forEach(n -> System.out.println("Name: " + n.getName() + " age: " + n.getAge()));
		
		humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()) * (-1));
		
		System.out.println("again list....");
		humans.forEach(n -> System.out.println("Name: " + n.getName() + " age: " + n.getAge()));
		
	}
	
	public static void main(String[] args) {
		Sorter instance = new Sorter();
		instance.sortTest();
	}
}

