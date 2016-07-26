package unicode;

import org.apache.commons.lang3.StringUtils;



public class ChineseTest {

	public static void main(String[] args) {
		ChineseTest tool = new ChineseTest();
		System.out.println(tool.containsChineseCharacter("Helloab!?12D#asDSF!&#$"));
		System.out.println(tool.containsChineseCharacter("你好	"));

	}
	
	protected boolean containsChineseCharacter(final String s)
	{
		if (StringUtils.isNotEmpty(s))
		{
			for (int i = 0; i < s.length();)
			{
				final int codepoint = s.codePointAt(i);
				System.out.println("Code Point: " + codepoint);
				i += Character.charCount(codepoint);
				System.out.println("i: " + i + " CharCount: " + Character.charCount(codepoint));
				if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN)
				{
					return true;
				}
			}
		}
		return false;
	}

}
