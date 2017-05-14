package corresponding;

public class PreSales {
	private String name;
	private String focusArea;
	private int salaryPlusBonus;
	
	public PreSales(String name, String focusArea, int salary){
		this.focusArea = focusArea;
		this.salaryPlusBonus = salary;
		this.name = name;
	}
	
	public PreSales(String name){
		this.name = name;
	}
	
	public String toString(){
		return "Presales: " + this.name + " focusArea: " + this.focusArea + " salaryPlusBonus: " + 
	         this.salaryPlusBonus;
	}
}
