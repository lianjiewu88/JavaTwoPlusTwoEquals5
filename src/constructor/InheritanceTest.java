package constructor;

class ctrPerson{
	private String name;
	public ctrPerson(String name){
		this.name = name;
		System.out.println("Hello, " + name);
	}
}

class ctrSubPerson extends ctrPerson{
	private int age;
	private ctrSubPerson(String name, int age){
		super(name);
		this.age = age;
		System.out.println("Hello, " + name + " years old: " + age);
	}
	
	static public ctrSubPerson getInstance(String name, int age){
		return new ctrSubPerson(name, age);
	}
}

class revertPerson{
	private String name;
	protected revertPerson(String name){
		this.name = name;
		System.out.println("Hello, " + name);
	}
}

class revertSubPerson extends revertPerson{
	private int age;
	public revertSubPerson(String name, int age){
		super(name);
		this.age = age;
		System.out.println("Hello, " + name + " years old: " + age);	
	}
}

public class InheritanceTest {

	public static void main(String[] args) {
		ctrPerson p1 = new ctrPerson("Jerry");
		ctrSubPerson p2 = ctrSubPerson.getInstance("Jerry", 34);
		
		revertSubPerson p3 = new revertSubPerson("Jerry", 34);
	}

}
