package interfaceTest;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser{

	public static void main(String[] args) {
		UserImpl tool = new UserImpl();
		tool.print("Jerry");
		
		tool.changetest(1);
	}

	@Override
	public void print(final String name) {
		System.out.println(name);
	}

	@Override
	public void changetest(int a) {
		a = 2;
		System.out.println("new value: " + a); // print 2
		final List<String> result = new ArrayList<>();
		result.add("2");
	}
	
	/* duplicate method
	@Override
	public void print(String name) {
		System.out.println(name);
	}*/
	
	

}
