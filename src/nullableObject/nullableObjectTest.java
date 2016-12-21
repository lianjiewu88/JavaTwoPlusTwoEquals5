package nullableObject;

import java.util.Objects;

public class nullableObjectTest {

	private String name = null;
	
	public void addToSegment(String name) {
		/*
		 * if (obj == null)
            	throw new NullPointerException();
           return obj;
		 */
		Objects.requireNonNull(this.name);
		this.name = name;
		System.out.println("length: " + name.length());
	    Objects.requireNonNull(this.name);
	}
	
	public static void main(String[] args) {
		nullableObjectTest tool = new nullableObjectTest();
		tool.addToSegment(null);
	}

}
