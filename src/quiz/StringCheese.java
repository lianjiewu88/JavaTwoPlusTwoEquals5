package quiz;

import java.io.UnsupportedEncodingException;

public class StringCheese {

	public static void main(String[] args) {
		/* 字符集：被编码的字符集合和字符编码模式的结合物。包含了字符，表示字符的数字编码即在字符编码

			序列和字节序列之间来回转换的方式。
		*/
		System.out.println("Default: " + java.nio.charset.Charset.defaultCharset()); // utf-8
		byte bytes[] = new byte[256];
		for( int i = 0; i< 256; i++)
		    bytes[i] = (byte)i;
		String str = null;
		try {
			str = new String(bytes, "ISO-8859-1"); // 字符和字节之前做11映射
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for( int i = 0, n = str.length(); i < n; i++) {
			char each = str.charAt(i);
			System.out.println("Charat: " + each);
			int eachI = (int)each;
			System.out.println("each int: " + eachI);
		   System.out.println(eachI + " " );
		}

	}

}
