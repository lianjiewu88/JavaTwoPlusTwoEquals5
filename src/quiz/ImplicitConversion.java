package quiz;

public class ImplicitConversion {

	public static void main(String[] args) {
		short x = 0;
		int i = 123456;

		x += i;

		//E1 op= E2 等价于E1 = (T) ((E1) op (E2)), 其中T是E1的类型。
		// 123456被转换成了short，short只有16位，因此int高16位全部被截断，剩下的低16位的第16位为1，因此最后是负数。
		System.out.println(x); // -7616
		
		System.out.println("H" + "a");
		System.out.println('H' + 'a');
		System.out.println("" + 'H' + 'a');
	}

}
