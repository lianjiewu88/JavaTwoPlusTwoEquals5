package interfaceTest;

public class Vicky implements Employee {

	@Override
	public void greet() {
		System.out.println("I am Vicky");

	}

	private String name;
	public Vicky(){
		this.name = "Vicky";
	}

	@Override
	public String getEmployeeName() {
		return this.name;
	}
}
