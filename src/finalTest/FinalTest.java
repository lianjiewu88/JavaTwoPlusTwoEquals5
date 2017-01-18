package finalTest;

public class FinalTest {
	static public void main(String[] arg){
		int x = 10; // still works with non-final value
		 
		new Runnable() {
		    @Override
		    public void run() {
		        System.out.println(x);
		    }
		}.run();
	}
}
