package performance;

class Child{
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	public Child(String name){
		this.name = name;
	}
}

public class MethodCallTest {

	private Child mChild;
	private long mMillis = 0;
	
	public Child getChild(){
		return this.mChild;
	}
	public MethodCallTest(String name){
		this.mChild = new Child(name);
	}
	
	public String getName1(){
		return this.mChild.getName();
	}
	
	public String getName2(){
		return getChild().getName();
	}
	public void start(){
		mMillis = System.currentTimeMillis ();
	}
	
	public long stop(){
		return System.currentTimeMillis () - mMillis;
	}
	
	public static void main(String[] args) {
		long N = 100000000l;
		MethodCallTest test = new MethodCallTest("Jerry");
		test.start();
		for( long i = 0; i < N; i++){
			test.getName1();
		}
		System.out.println("end1: " + test.stop());
		
		test.start();
		for( long i = 0; i < N; i++){
			test.getName2();
		}
		System.out.println("end2: " + test.stop());
		
		test.start();
		Child child = test.getChild();
		for( long i = 0; i < N; i++){
			child.getName();
		}
		System.out.println("end3: " + test.stop());
	}

}
