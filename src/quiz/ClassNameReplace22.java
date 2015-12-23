package quiz;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassNameReplace22 {

	public static void main(String[] args) {
		System.out.println(ClassNameReplace22.class.getName());
		System.out.println(ClassNameReplace22.class.getName().replaceAll("\\.", "/") + ".class");
		// does not work, 把所有的字符 - 因为.匹配所有字符 - 全部替换成/
		System.out.println(ClassNameReplace22.class.getName().replaceAll(".", "/") + ".class");
		String result = Pattern.quote(".");
		System.out.println(result); // -> \\Q.\\E
		System.out.println(ClassNameReplace22.class.getName().replaceAll(result, "/") + ".class");
		
		// Exception in thread "main" java.lang.IllegalArgumentException: character to be escaped is missing
		/* replace的第二个参数不是一个普通的字符串，而是一个替代字符串，替代字符串中出现的反斜杠会把

		紧随其后的字符进行转义，从而导致其被按字面含义处理。
		 */
		// System.out.println(ClassNameReplace22.class.getName().replaceAll(result, File.separator) + ".class");
		
		String normal = File.separator;
		String after = Matcher.quoteReplacement(File.separator);
		System.out.println(normal); // \\
		http://www.baidu.com
		System.out.println(after);  // \\\\
		StringBuffer a = new StringBuffer('a'); // 'a' is interpreted as 97
		System.out.println(a.length()); // 0
		System.out.println(a.capacity()); // 97 
	}

}
