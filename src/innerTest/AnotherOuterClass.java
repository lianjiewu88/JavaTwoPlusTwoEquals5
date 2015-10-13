package innerTest;

public class AnotherOuterClass{
	public static void main(String[]args) { 
		InnerClass inner = new AnotherOuterClass().new InnerClass();
		System.out.println("InnerClass Filed = "+inner.x);
	}
	
	class InnerClass { 
		private int x =10;
	}
}
