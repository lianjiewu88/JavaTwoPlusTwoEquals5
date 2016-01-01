package quiz;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Exception in thread "main" java.lang.IllegalAccessException: Class quiz.Reflector78 can not access a member of class java.util.HashMap$HashIterator with modifiers "public final"
	at sun.reflect.Reflection.ensureMemberAccess(Unknown Source)
	at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(Unknown Source)
	at java.lang.reflect.AccessibleObject.checkAccess(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at quiz.Reflector78.main(Reflector78.java:14)
	
	hasNext是public方法，但是该方法是在私有的嵌套类实现的。这个类不是public，来自另一个包。访问其

他包中的非公共类型的成员是不合法的。it.getclass返回的是迭代器的动态类型，是私有的嵌套类。
方法调用都使用接口进行，可以将调用方法的类与实现这些方法的类隔离开，类型安全。在服务提供者框

架中很常见。访问其他包中的非公共类型的成员是不合法的，即使这个成员同时也被声明为某个公共类型

的公共成员。
	*/
public class Reflector78 {
	  public static void main(String[] args) throws Exception {
	     Set<String> s = new HashSet<String>();
	     s.add("foo");
	     @SuppressWarnings("rawtypes")
		Iterator it = s.iterator();
	     
	     Object a = it.getClass();
	     System.out.println("GetClass: " + a.toString()); // class java.util.HashMap$KeyIterator
	     // Method m = it.getClass().getMethod("hasNext");
	     // System.out.println(m.invoke(it));
	     Method m = Iterator.class.getMethod("hasNext");
	     System.out.println(m.invoke(it));
	     
	  }
	}