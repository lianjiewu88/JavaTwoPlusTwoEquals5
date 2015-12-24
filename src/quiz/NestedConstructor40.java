package quiz;

public class NestedConstructor40 {

	private NestedConstructor40 internal = new NestedConstructor40();
	
	public NestedConstructor40() throws Exception {
	      throw new Exception("I am not coming out");
	}
	
	/* Exception in thread "main" java.lang.StackOverflowError
	at quiz.NestedConstructor40.<init>(NestedConstructor40.java:5)
	StackOverflowError是Error的子类型而不是Exception的子类型，因此catch无法捕获
	*/
	public static void main(String[] args) {
		try {
			   NestedConstructor40 b = new NestedConstructor40();
			   System.out.println("Surprise!");
			} catch ( Exception ex) {
			  System.out.println("I told you so");
			}

	}

}
