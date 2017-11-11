package twoColorBall;

public class Configuration
{
	static public final int MaxDigit = 7;
	static public final int HIGHEST_RATE = 0;
	static public final int LOWEST_RATE = 1;
	static public final int MEDIUM_RATE = 2;
	static private final boolean debug_on = true;
	
	static public void trace(String value)
	{
		if( debug_on)
			System.out.println(value);
	}
}