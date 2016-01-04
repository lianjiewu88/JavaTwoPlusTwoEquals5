package quiz;

import java.util.Arrays;

class ImportDuty71 {
   public static void main(String[] args ) {
        printArgs(1,2,3,4);
   }

   // The method toString() in the type object is not applicable for the arguments( Object[] )
   /* 
    * 
    * Quiz 72: final修饰符对于方法和域而言，完全不同。对于方法，final意味着该实例方法不能被覆写

，或者静态方法不能被隐藏。对于域，final意味着其不能被赋值超过一次，但是可以被隐藏。

编译器尝试去应用Object.toString().
编译器在选择运行期将要被调用的方法时，所做的第一件事就是在肯定能找到该方法的范围内挑选。将

在具有恰当名字的方法的最小闭合范围内挑选。这个范围就是ImportDuty类。包含了从Object继承而来

的toString方法。我们在代码里通过import导入的方法被ImportDuty从Object那里继承而来的具有相同

名字的方法所遮蔽shade了。
遮蔽与遮掩obscure非常类似，二者区别是一个声明只能遮蔽类型相同的另一个声明：将遮蔽看成一个

二元操作符，其操作数并且同质，比如都是类型声明，或者都是方法声明。而这样obscure可以遮掩类

型和包声明。本身就属于某个范围的成员在该范围内与静态导入相比具有优先权。

final 方法不能被覆写-对实例方法而言，或者不能被隐藏，对静态方法而言。对于field，final意味

着不能被赋值超过一次。

*/
   static void printArgs(Object... args) {
       // System.out.println(toString(args));
	   System.out.println(Arrays.toString(args));
   }
}
