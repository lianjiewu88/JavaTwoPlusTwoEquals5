package springSimulation;

public class Helloimplements implements IHello {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name);  

	}

	@Override
	public void sayGoogBye(String name) {
		System.out.println(name+" GoodBye!");  
	}
}
