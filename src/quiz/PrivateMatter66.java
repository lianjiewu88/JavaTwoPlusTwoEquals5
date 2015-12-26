package quiz;

class Base {
	   public String className = "Base";
	   public void hello() {
		   
	   }
	}

class Derived extends Base {
	   private String className = "Derived"; // 把base class的field隐藏了
	   //private void hello() { // cannot reduce the visibility of inherited method from base
	   public void hello(){ // this is ok
		   
	   }
	}



class PrivateMatter66 {
		
	public static void main(String[] args) {
		// Derived.className is not visible.
		// System.out.println(new Derived().className);
		/* 一旦一个方法在子类中被覆写，你就不能在子类的实例上调用它了，除了在子

		类内部，通过使用super关键字来call。但是，可以通过将子类实例转型为某个

		超类类型来访问到被隐藏的域，在这个超类中该域未被隐藏。
		你能够对基类所做的任何事，都同样能够作用于其子类。如果一个类要隐藏一

个域，而用来隐藏该域的域具有的可访问性比被隐藏域更具限制性，就违反了liskov置换原则。
		 */
		System.out.println(((Base)new Derived()).className);
	}

}
