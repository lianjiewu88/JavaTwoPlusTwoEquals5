package exception;

import java.util.ArrayList;

public class exceptionTest{
	static public void main(String[] arg){
		try{
			ArrayList<String> test = null;// new ArrayList<String>();
			//System.out.println(test.size());
			return;
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
		finally{
			System.out.println("In finally");
		}
		// System.out.println("hello world");
	}
}