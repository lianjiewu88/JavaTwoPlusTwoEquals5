package quiz;

public class Twisted92 {

	private final String name;
	Twisted92(String name) {
	     this.name = name;
	}

	private String name() {
	    return name;
	}

	private void reproduce() {
		// 所有的本地，内部，嵌套和匿名的类都可以毫无限制的访问彼此成员。
	    new Twisted92("reproduce") {
	      void printName() {
	        System.out.println(name());
	      }
	   }.printName();
	}

	/* name方法并没有被继承到reproduce方法中的匿名类中。所以，匿名类中对于printName方法的调用必须

关联到外围实例main而不是当前实例reproduce。
enclosing scope:含有正确名称的方法的最小外围范围。

*/
	public static void main(String[] args) {
		new Twisted92("main").reproduce();
	}

}
