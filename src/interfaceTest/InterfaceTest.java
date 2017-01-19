package interfaceTest;

interface IF_Java{
	public int getVersion();
}

class CL_Java implements IF_Java{
	static public IF_Java CreateVersionAPI(){
		return new CL_Java();
	}

	@Override
	public int getVersion() {
		return 1;
	}
}

class PureTest{
	private String name;
	public PureTest(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void say(){
		System.out.println("I am: " + this.name);
	}
}


public class InterfaceTest {

	private static void hello1(PureTest test){
		test.say();
		test = null;
	}
	
	private static void hello2(PureTest test){
		test.setName("Tom");
	}
	
	private static void hello3(PureTest test){
		test = null;
		test = new PureTest("Jay");
	}
	public static void main(String[] args) {
		IF_Java result = CL_Java.CreateVersionAPI();
		System.out.println(result.getVersion());
		
		PureTest test1 = new PureTest("Jerry");
		test1.say();
		hello1(test1);
		test1.say();
		/*
		 * I am: Jerry
			I am: Jerry
			I am: Jerry
		 */
		
		hello2(test1);
		test1.say(); // I am: Tom
		
		hello3(test1);
		test1.say(); // I am: Tom
	}

}
