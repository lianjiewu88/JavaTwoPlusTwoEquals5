package interfaceTest;

public class Jerry implements Employee {

	@Override
	public void greet() {
		System.out.println("I am Jerry");
	}
	
	private String name;
	public Jerry(){
		this.name = "Jerry";
	}
	
	@Override
	public String getEmployeeName() {
		return this.name;
	}

}
