package quiz;

public class EscapeRout14 {

	public static void main(String[] args) {
		// 编译器首先将Unicode转义字符转换成它们所表示的字符，对\u0022,转换成"",
		// <=>于"a".length() + "b".length() = 2
		
		/* from Funi t s // syntax error: invalid unicode \ u 后面必须跟四个十六进制的数字。
		*/
		
		System.out.println("a\u0022.length()+\u0022b".length()); // 2
		// Unicode的转义字符真正被用于只能使用ASCII字符的程序中插入一个Unicode字符。一个Unicode转义字
		// 符精确地等价于其所表示的字符。
		/* 用于程序员需要插入一个不能用源文件字符集表示的字符的情况，主

			要用于将非ASCII字符至于标识符, 字符串字面常量，字符字面常量即注释中。
			unicode转义字符必须是良构的，即使出现在注释中。
			
		 */
		

	}

}
