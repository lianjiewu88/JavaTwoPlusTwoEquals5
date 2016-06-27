package basic;

public class finalizeTest {

	private String name;
	public void finalize(){
		System.out.println("finalize called: " + this.name);
		// System.out.println("Thread id in finalize: " + Thread.currentThread().getId());
	}
	
	public finalizeTest(String name){
		this.name = name;
	}
	
	public static void main(String[] args) {
		
		Object object = null;
		System.out.println("Thread id: " + Thread.currentThread().getId());
		finalizeTest test = new finalizeTest("Jerry");

		new finalizeTest("Scala");

		System.gc();
	}

}
