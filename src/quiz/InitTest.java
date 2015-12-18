package quiz;

public class InitTest {
	public class SuperClass {
		private int mSuperX;
		public SuperClass() {
			setX(99);
		}
		public void setX(int x) {
			mSuperX = x;
		}
	}
	
	public class SubClass extends SuperClass {
		private int mSubX = 1;
		// System.out.println("Hehe"); Syntax error
		public SubClass() { 
			System.out.println("subclass!");
		}
		
		@Override
		public void setX(int x) {
			super.setX(x);
			mSubX = x;
			System.out.println("SubX is assigned " + x);
		}
		
		public void printX() {
			System.out.println("SubX = " + mSubX);
		}
	}
	
	public void run(){
		SubClass sc = new SubClass();
		sc.printX();
	}
	

	public static void main(String[] args) {
		InitTest test = new InitTest();
		test.run();
	}

}
