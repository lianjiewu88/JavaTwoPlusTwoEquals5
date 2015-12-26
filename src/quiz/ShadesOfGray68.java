package quiz;

public class ShadesOfGray68 {
	   public static void main(String[] args){
		   //当一个变量和一个类型具有相同的名字，而且位于相同的作用域时。变量名具有优先权。变量名将

		   // obscure类型名。相应的，变量名和类型名可以遮掩包名。
	       // System.out.println(X.Y.Z); // white
		   
		   /* 引用到一个被obscure的类型名的，其技巧是在某一种特殊的语法上下文环境中使用该名字，在该语法

			   上下文环境中允许出现一个类型但是不允许出现一个变量。在转型表达式的括号中间的部分就是这样一

			   种上下文环境。*/

		   Object o = new Object();
		   X.Y o1 = (X.Y)null; // why (X.Y)o就error？
		   System.out.println(((X.Y)null).Z); // black
		   System.out.println(o1.Z);
		   System.out.println(Xy.Z); 
		   adv();
	   }
	   // 基类总是一种类型，出现在extends子句中的名字从来都不会被解析为变量名。
	   static class Xy extends X.Y {
		   
	   }
	   /* 一个包内私有的方法不能被位于另一个包中的某个方法直接覆写。不同包内的private方法彼此独立

	   。如果包A想overwrite包B的方法，需要在B的方法前加上public或者protected。并且在包A的方法前，

	   或者两个方法都是public，或者都是protected，或者B是protected，A是public。
	   包内私有的方法是它们所属包的实现细节，在包外重用它们的名字是不会对包内产生任何影响的。
	   */
	   
	   public static <T extends X.Y> void adv() {
	       System.out.println(T.Z);
	   }
	   
}

class X {
	   static class Y {
	      static String Z = "Black";
	   }
	   static C Y = new C();
	}

	class C {
	   String Z = "White";
	}
	
