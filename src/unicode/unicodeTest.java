package unicode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class unicodeTest {

	public static void main(String[] args) {
		String shang = "殇";
		String result = "";
		System.out.println(shang);
		try {
			result = URLEncoder.encode(shang, "GBK");
			System.out.println("GBK:" + result);
			
		    result = URLEncoder.encode(shang, "UTF-8");
			System.out.println("UTF8: " + result);
			
			result = URLEncoder.encode(shang, "UTF-16");
			System.out.println("UTF16:" + result);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}

}
