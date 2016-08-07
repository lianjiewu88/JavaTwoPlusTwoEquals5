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
	
	public MethodCallTest(String name){
		this.mChild = new Child(name);
	}
	
	public String getName(){
		return this.mChild.getName();
	}
	public void start(){
		mMillis = System.currentTimeMillis ();
	}
	
	public long stop(){
		return System.currentTimeMillis () - mMillis;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
