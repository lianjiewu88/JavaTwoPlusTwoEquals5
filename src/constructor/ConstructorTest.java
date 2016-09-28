package constructor;

class Super {
    String s;
    public Super(String s){
       this.s = s;
    }
}
/*
 * Jerry 2016-01-15 21:17PM
 * Jerry 2016-09-28 15:30PM - I am back
 * 编译出错的原因主要是默认的Super类的构造函数并没有被定义。在Java中，如果一个类没有实现构造函数，编译器默认会给这个类插入一个无参构造函数。如果在父类中已经有了构造函数，那么编译器将不会再插入默认无参构造函数。
子类的构造函数，要么是带参的要么是无参的，都会调用父类的无参构造。因为编译器想在子类中，加入super()，但是父类的无参构造函数并不存在。所以，编译器会报错。
为了解决这个问题，有两种解决办法，第一，在Super类中，加入无参构造：
public Super(){
    System.out.println("Super");
}
第二种方式，移除自定义的父类构造函数
第三种方式，子类的构造函数中加入super(value)
 */

class Sub extends Super {
     public Sub(String s ) {
    	 super(s);
     }
     public Sub(){
    	 super("dummy");
     }
}


public class ConstructorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
