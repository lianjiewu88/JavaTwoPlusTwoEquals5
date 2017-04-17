package proxy;

public final class ProductOwnerSCProxy {
	private String name;

	public ProductOwnerSCProxy(String name){
		this.name = name;
	}

		public ProductOwnerSCProxy(){};
		public void defineBackLog(){
		System.out.println("PO: " + name + " defines Backlog.");}}
