package twoColorBall.test;


import java.sql.Timestamp;
import java.util.Random;

public class RandomTest
{
	public static void main(String[] args) 
	{
		Timestamp d = new Timestamp(System.currentTimeMillis());
		int b = d.hashCode();
		System.out.println("timestamp: " + b);
		Random random1 = new Random(b);
		int a = random1.nextInt();
		if ( a < 0 )
			a = -a;
		System.out.println(a);
		System.out.println("Result: " + ( a + 825281 ) % 3);

	}
}