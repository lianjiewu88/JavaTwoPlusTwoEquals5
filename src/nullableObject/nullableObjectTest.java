package nullableObject;

import java.util.Objects;

class Outer {
    Nested nested;
    Nested getNested() {
        return nested;
    }
}
class Nested {
    Inner inner;
    Inner getInner() {
        return inner;
    }
}
class Inner {
    String foo = "Jerry";
    String getFoo() {
        return foo;
    }
}



public class nullableObjectTest {
	
	public void test1(){
		Outer outer = new Outer();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
		    System.out.println(outer.nested.inner.foo);
		}
	}

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
		// tool.addToSegment(null);
		
		tool.test1();
	}

}
