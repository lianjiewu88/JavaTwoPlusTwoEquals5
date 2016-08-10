package interfaceTest;

abstract class AbstractTest {
	abstract public void method1();
	protected String mName;
	public AbstractTest(final String name)
	{
		this.mName = name;
	}
}

class ChildTest extends AbstractTest{

	public ChildTest(String name) {
		super(name);
	}

	@Override
	public void method1() {
		System.out.println("Hello: " + this.mName);
	}
	
	static public void main(String[] arg){
		ChildTest child = new ChildTest("Jerry");
		child.method1();
		
		// compile error
		// AbstractTest a = new AbstractTest("Jerry");
	}
}
