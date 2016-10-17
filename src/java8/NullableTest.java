package java8;

import java.util.Optional;

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
    String foo;
    String getFoo() {
        return foo;
    }
}


public class NullableTest {
	public static Outer getInitializedOuter(){
		Outer outer = new Outer();
		outer.nested = new Nested();
		outer.nested.inner = new Inner();
		outer.nested.inner.foo = "Jerry";
		return outer;
	}
	
	/*private static void way0(){
		Outer outer = new Outer();
		System.out.println(outer.nested.inner.foo);
	}*/
	public static void way1(){
		Outer outer = getInitializedOuter();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
		    System.out.println(outer.nested.inner.foo);
		}
	}
	
	
	public static void way2(){
		Optional.of(getInitializedOuter())
	    .map(Outer::getNested)
	    .map(Nested::getInner)
	    .map(Inner::getFoo)
	    .ifPresent(System.out::println);
	}
	public static void main(String[] args) {
		//way0();
		way1();
		//way2();
	}

}
