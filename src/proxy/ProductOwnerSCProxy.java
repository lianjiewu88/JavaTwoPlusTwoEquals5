package proxy;

public final class ProductOwnerSCProxy {
	private String name;

	public ProductOwnerSCProxy(String name){
		this.name = name;
	}
	public void defineBackLog(){
		System.out.println("PO writes some document before defining BackLog");		
		System.out.println("PO: " + name + " defines Backlog.");
	}
}
