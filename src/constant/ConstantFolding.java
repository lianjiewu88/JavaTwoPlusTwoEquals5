package constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantFolding {

	/*static final  int number1 = 512;
	 
    static final  int number2 = 623;
 
    static int number3 = 545;
 
    static int number4= 619;*/

	public static void main(String[] args) {
		 /*int product1 = number1 * number2;         
         int product2 = number3 * number4;         
         System.out.println("Value: " + product1 + " , " + product2);*/
		// converted: List myList = new ArrayList(10);
		/*
		 * 泛型（又称类型检验）：这个是发生在编译期的。
		 * 编译器负责检查程序中类型的正确性，然后把使用了泛型的代码翻译或者
		 * 重写成可以执行在当前JVM上的非泛型代码。这个技术被称为“类型擦除“。
		 * 换句话来说，编译器会擦除所有在尖括号里的类型信息，来保证和版本1.4.0或者
		 * 更早版本的JRE的兼容性。
		 */
		
		List<String> myList = new ArrayList<String>(10);
	}

}
