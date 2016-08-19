package interfaceTest;

import java.util.ArrayList;
import java.util.List;

public class JavaPTest {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Jerry());
		list.add(new Vicky());
		
		Employee jerry = getEmployeeByName(list, "Jerry");
		jerry.greet();
		
		Employee vicky = getEmployeeByName(list, "Vicky");
		vicky.greet();
	}

	public static Employee getEmployeeByName(List<Employee> list, String name){
		return list.stream().filter( (p) -> p.getEmployeeName().equals(name)).findFirst().get();
	}
}
