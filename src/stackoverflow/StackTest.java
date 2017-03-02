package stackoverflow;

import java.util.ArrayList;

public class StackTest {

	public static void main(String[] args) {
		Object[] stack = new Object[10];
		stack[0] = "1";
		stack[1] = 2;
		stack[2] = new ArrayList<String>();
		System.out.println("stack: " + stack);
 		

	}

}
