package quiz;

public class NestedClass90 {

	class Inner1 extends NestedClass90 {
		public Inner1(){
			super();
		}
	} // so far ok
	// class Inner2 extends NestedClass90 {} // also ok
	// No enclosing instance of type NestedClass90 is available 
	// due to some intermediate constructor invocation
	
	
	class Inner2 extends Inner1 {
		public Inner2() {
			/*
			 * Inner2的超类本身也是一个内部类，要想实例化一个内部类，例如Inner1，需要提供一个外部类的实例

给构造器。一般情况下，它是隐式地传递给构造器的，但是也可以通过expression.super(args)的方式

通过超类构造器调用显式地传递。如果外部类实例是隐式传递的，编译器会自动产生表达式，它使用

this来指代最内部的其超类是一个成员变量的外部类。Inner2间接扩展了Quiz90类，Inner便是它的一

个继承而来的成员。因此，超类构造器的限定表达式直接就是this，编译器提供外部类实例，将super

重写成this.super. 
			 */
			NestedClass90.this.super();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
