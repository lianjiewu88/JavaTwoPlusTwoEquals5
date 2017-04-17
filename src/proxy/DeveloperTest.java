package proxy;

public class DeveloperTest {

	public static void main(String[] args) {
		
		IDeveloper jerry = new Developer("Jerry");
		ITester sara = new Tester("Sara");
		
		IDeveloper jerryProxy = (IDeveloper) new EnginnerProxy().bind(jerry);
		jerryProxy.writeCode();
		
		ITester saraProxy = (ITester) new EnginnerProxy().bind(sara);
		saraProxy.doTesting();
	}
}
