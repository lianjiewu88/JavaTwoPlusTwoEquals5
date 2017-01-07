package jmap;

class Tool{
	private int count = 0;
	public void Run() throws InterruptedException{
		while(true){
			System.out.println("Hello: " + this.count++);
			Thread.sleep(5000);
		}
	}
}
public class JMapTest {

	public static void main(String[] args) throws InterruptedException {
		Tool tool = new Tool();
		tool.Run();
	}
}
