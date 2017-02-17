package sugar;

import java.util.ArrayList;
import java.util.List;

// Java中最常用的语法糖主要是**泛型、变长参数、自从装箱/拆箱、遍历循环**，
// JVM在运行时不支持这些语法，它们在编译阶段还原回简单的基础语法结构，
// 这个过程也就是解语法糖。举个泛型擦除的例子，List<Integer>和List<String>
// 在编译之后会进行泛型擦除，变成一样的原生类型List<E>。
public class GrammarSugar {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		
		List b = new ArrayList();
		
		a.add("1");
		b.add("2");

	}

}

/*
compiled:
package sugar;

import java.util.ArrayList;
import java.util.List;

public class GrammarSugar
{
  public static void main(String[] args)
  {
    List a = new ArrayList();
  }
}
*/