package proxy;

public class DeveloperTest {

	public static void main(String[] args) {
		
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		
		IDeveloper jerry = new Developer("Jerry");
		ITester sara = new Tester("Sara");
		
		IDeveloper jerryProxy = (IDeveloper) new EnginnerProxy().bind(jerry);
		jerryProxy.writeCode();
		
		ITester saraProxy = (ITester) new EnginnerProxy().bind(sara);
		saraProxy.doTesting();
	}
}
