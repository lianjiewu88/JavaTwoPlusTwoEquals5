package dynamicproxy;

public class DynamicProxy implements IHelloWorld
{
	IHelloWorld helloWorld;

	public DynamicProxy(IHelloWorld helloWorld)
	{
		this.helloWorld = helloWorld;
	}

	public void print()
	{
		System.out.println("Before Hello World!");
		helloWorld.print();
		System.out.println("After Hello World!");
	}
}