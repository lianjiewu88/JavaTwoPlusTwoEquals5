package castTest;

// http://stackoverflow.com/questions/380813/downcasting-in-java
public class DownCast {

	//Downcasting is allowed when there is a possibility that it suceeds at run time:
	private static void scenario1(){
		Object o = "Jerry"; // You can not use any method of String based on o reference
		String s = (String) o; // this is allowed because o could reference a String
		
		System.out.println("Hello: " + s);
	}
	
	private static void scenario2(){
		Object o = new Object();
		try{
			String s = (String) o; // this will fail at runtime, because o doesn't reference a String
			// Note that some casts will be disallowed at compile time, because they will never succeed at all:

				Integer i = 1;
			//	String s2 = (String) i; // the compiler will not allow this, since i can never reference a S
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
		}
	}
		
	
	public static void main(String[] args) {
		scenario1();
		scenario2();
	}

}
