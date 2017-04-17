package proxy;

public class DeveloperTest {

	public static void main(String[] args) {
		
		ITester sara = new Tester("Sara");
		ITester saraProxy = new TesterProxy(sara);
		saraProxy.doTesting();
	}
}
