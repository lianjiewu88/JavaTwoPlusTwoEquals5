package proxy;

public final class ProductOwner {
	private String name;
	public ProductOwner(String name){
		this.name = name;
	}
	
	public ProductOwner(){
		
	}
	public void defineBackLog(){
		System.out.println("PO: " + name + " defines Backlog.");
	}
}
