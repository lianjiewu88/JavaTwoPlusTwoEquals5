package quiz;

public class Greeter81 {

	/* System.out是带有缓冲的，hello world中的字符被写入了System.out的缓冲区，但是缓冲区从来都没

有被flush, 大多数程序员认为，当有输出产生时System.out和System.err会自动地进行刷新，这并不

完全正确。PrintStream什么时候自动刷新？当一个字节数组byte array被写入，或者某个println被调

用，或者换行字符或\n被写入，PrintStream就会自动被调用。
*/
	public static void main(String[] args) {
		String greeting = "Hello World";
		for( int i = 0; i < greeting.length(); i++)
		   System.out.write(greeting.charAt(i));
		// solution
		System.out.flush();

	}

}
