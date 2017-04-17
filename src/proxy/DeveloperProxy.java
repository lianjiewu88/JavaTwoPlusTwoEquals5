package proxy;

public class DeveloperProxy implements IDeveloper{
	private IDeveloper developer;
	public DeveloperProxy(IDeveloper developer){
		this.developer = developer;
	}
	@Override
	public void writeCode() {
		System.out.println("Write documentation...");
		this.developer.writeCode();
	}
}
