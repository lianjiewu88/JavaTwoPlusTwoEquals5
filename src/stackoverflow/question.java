package stackoverflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class question {

	@SuppressWarnings("unused")
	private static void dateTest() throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str3 = "1927-12-31 23:54:07";
		String str4 = "1927-12-31 23:54:08";
		Date sDt3 = sf.parse(str3);
		Date sDt4 = sf.parse(str4);
		long ld3 = sDt3.getTime() /1000;
		long ld4 = sDt4.getTime() /1000;
		System.out.println(ld4-ld3);
	}
	public static void main(String[] args) throws ParseException {
		int i = 5;
		long j = 8;
		// Type mismatch: cannot convert from long to int
		// i = i + j;
		/* JLS里有答案。参见 §15.26.2复合赋值运算符。摘录：

		E1 op= E2 型的复合赋值表达式等价于 E1 = (T)((E1) op (E2))，这里 T 是 E1 的类型，不同的是 E1 只计算一次。
		*/
		i += j;
		/*
		 * 下面的代码是正确的：
		short x = 3;
		x += 4.6;
		x的结果等于7，因为它等价于：
		short x = 3;
		x = (short)(x + 4.6);
		 */
		System.out.println(i);
	}

}
