package quiz;

public class HexTestQuiz5 {

	public static void main(String[] args) {
		// System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
		// 268435456 后者实际等于-889275714
		// System.out.println((int)(char)(byte)-1); // 65535
		//System.out.println((int)(char)(byte)1);
		
		char a = '1';
		int c = a;
		char b = (char) -1;
		System.out.println("c: " + c);
		System.out.println("b: " + b);
		
		/*一个操作数的类型是T，包含byte,short或者char。而另一个数是int类型的常量表达式，其值可以用T

		表示，则条件表达式的类型就是T。
		否则，将对操作数类型运用二进制数字提升，条件表达式的类型为第二个和第三个操作数被提升之后的

		类型。
		
		对于false?i:x,因为这里的i是变量，不满足int类型的常量表达式要求。 
		*/
		char x = 'X';
		int i = 0;
		System.out.println(true?x:0);
		System.out.println(false?i:x); // 实际上调用的println传入的参数类型是int，‘X’对应的ascii是88
		
	}

}
