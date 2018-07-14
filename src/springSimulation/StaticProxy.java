package springSimulation;

public class StaticProxy implements IHello {

	private IHello iHello;
	
	public void setImpl(IHello impl){
		this.iHello = impl;
	}
	@Override
	public void sayHello(String name) {
		System.out.println("问候之前的日志记录...");
		iHello.sayHello(name);

	}

	@Override
	public void sayGoogBye(String name) {
		System.out.println("问候之前的日志记录...");
		iHello.sayGoogBye(name);
	} 
	
	static public void main(String[] arg) {
		Helloimplements hello = new Helloimplements();
		
		StaticProxy proxy = new StaticProxy();
		
		proxy.setImpl(hello);
		
		proxy.sayHello("Jerry");
	}
}
