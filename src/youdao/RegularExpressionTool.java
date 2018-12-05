package youdao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTool {
	public static void main(String[] args) {
		String str = "adasd src=\"http://1.jpg\" dsfdsf src=\"http://2.jpg\" end";

        String img = "";
        Pattern p_image;
        Matcher m_image;
        
        
        Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(str);
        while (m.find()) {
                System.out.println(m.group(1));
            }
        }
}
