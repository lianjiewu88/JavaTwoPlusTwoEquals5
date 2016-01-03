package quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Jerry {
	
}
public class Pair88<T> {
	   private final T first;
	   private final T second;
	   public Pair88(T first, T second) {
	       this.first = first;
	       this.second= second;
	   }
	   public T first() {
	       return first;
	   }
	   public T second() {
	       return second;
	   }
	   public List<String> stringList() {
	      return Arrays.asList(String.valueOf(first), String.valueOf(second));
	   }
	   
	   public static void main(String[] arg) {
		   // Pair88 is a raw type. References to generic type Pair88<T> should be parameterized
		   // Pair88 p = new Pair88<Object>(23, "Jerry"); does not work 
		   // Pair88<Object> p = new Pair88<Object>(23, "Jerry"); work
		   Pair88<String> p = new Pair88<String>("23", "Jerry"); // also work
		   System.out.println(p.first() + " - " + p.second());
		   // Type mismatch: cannot convert from element type Object to String
		   /*
		    * 一个原生类型就是一个没有任何类型参数的泛型类的名字。List<E>是泛型接口，List<String>是参数

化的类型，List是一个原生类型。一个原生类型的所有势力成员都要被替换掉，在一个实例方法声明中

出现的每个参数化的类型都要被其对应的原生部分所取代。
变量p是属于原生类型Pair，所以其所有实例方法都要执行这种擦除。包括声明返回List<String>的方

法stringList. 编译器会将这个方法解释为返回原生类型List。
参数化类型List<String>虽然是方法stringList的返回类型，但它与Pair88的类型参数没有关系，事实

上最后被擦除了。
		    */
		   
		   for( String s: p.stringList())
			     System.out.println(s + ",");
		   List<?> a = new ArrayList<String>();
		   a.add(null);
		   // The method add(capture#2-of ?) in the type List<capture#2-of ?> is not applicable for the arguments (String)
		   // a.add("String");
		   System.out.println("size: " + a.size());
		   
		   /* 每次调用getAnnotation方法时都会涉及到两个Class对象。
		   1. 在其上调用该方法的对象 - 通过反射获得
		   2. 作为传递参数指出需要哪个类的注解的对象 - 类名称字面常量。 */
		   try {
			   // Class<?> java.lang.Class.forName(String className) 
			Class<?> b = Class.forName("quiz.Pair88");
			/*
			 * <T extends Annotation> T 
			 * java.lang.reflect.AnnotatedElement.getAnnotation(Class<T> annotationClass)
			 */
			// Pair88 ss = b.getAnnotation((Class<?>) Pair88.class);
			// Object ss = Class.forName("quiz.Pair88").getAnnotation();
			System.out.println(b.toString());
			
			Class<?> jerry = Class.forName("quiz.Jerry");
			// jerry.getAnnotation(Jerry.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }
}
