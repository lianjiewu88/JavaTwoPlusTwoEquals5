package twoColorBall.numberTool;

import java.sql.Timestamp;
import java.util.Random;

public class RandomGenerator
{
	static public int getRandom( int index)
	{
		Timestamp d = new Timestamp(System.currentTimeMillis());
		int seed = d.hashCode();
		Random random1 = new Random(seed);
		int number = random1.nextInt();
		if ( number < 0 )
			number = -number;
		int result = ( number + 825281 ) % index;
		//System.out.println("Random Number: " + result);
		return result;
	}
	
}