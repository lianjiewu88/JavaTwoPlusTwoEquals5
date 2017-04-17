package proxy;

public class DeveloperTest {

	public static void main(String[] args) {
		IDeveloper jerry = new Developer("Jerry");
		jerry.writeCode();
		
		IDeveloper jerryProxy = new DeveloperProxy(jerry);
		jerryProxy.writeCode();
	}
}
