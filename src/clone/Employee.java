package clone;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Cloneable {
	private String name;
	
	private List<String> skill = new ArrayList<String>();
	
	public Employee(String name){
		this.name = name;
	}
	
	public Employee addSkill(String name){
		this.skill.add(name);
		return this;
	}
	
	public static void main(String[] arg){
		Employee jerry = new Employee("Jerry");
		jerry.addSkill("ABAP");
		jerry.addSkill("Java");
		jerry.addSkill("JavaScript");
		
		Employee ji = null;
		
		try {
			ji = (Employee) jerry.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println("Clone done");
	}
}
