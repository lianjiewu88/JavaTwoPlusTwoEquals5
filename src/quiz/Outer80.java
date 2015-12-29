package quiz;

import java.lang.reflect.Constructor;

public class Outer80 {
	
	public static void main(String[] args) throws Exception {
	/* Exception in thread "main" java.lang.InstantiationException: quiz.Outer80$Inner
	at java.lang.Class.newInstance(Unknown Source)
	at quiz.Outer80.hello1(Outer80.java:10)
	at quiz.Outer80.main(Outer80.java:6)
Caused by: java.lang.NoSuchMethodException: quiz.Outer80$Inner.<init>()
	at java.lang.Class.getConstructor0(Unknown Source)
	*/
		new Outer80().hello1();
	}
	
	/*如果class对象代表了一个抽象类，一个接口，一个数组类，一个原始类型，或者是空，或者这个类没有任何空的，即无参构造器，或者实例化由于某种其他原因失败，则它会抛出异常。

Outer.Inner没有空构造器。
一个非静态的嵌套类的构造器，在编译时会将一个隐藏的参数作为其第一个参数，这个参数表示了它的直接外围实例-immediately enclosing instance. 当你在代码中任何可以让编译器找到合适的外围实例的地方去调用构造器时，这个参数就会被隐式传递进去。但是，上述过程只适用于普通的构造器调用，即不使用反射的情况。若用反射，该隐藏参数需要被显式传递。Class.newInstance做不到。
*/
    private void hello1() throws Exception {
    	try{
        // System.out.println(Inner.class.newInstance());
    		Constructor c = Inner.class.getConstructor(Outer80.class);
    		System.out.println(c.newInstance(Outer80.this));
    	}catch (Exception e){
    		System.out.println(e.getLocalizedMessage());
    	}
    }
    // 如果Inner实例不需要一个外围的outer实例，可以将其声明为静态的。
    // public static class Inner {}
    public class Inner{
      public String toString(){
        return "Hello";
    }
  }


	

}
