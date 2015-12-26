package quiz;

public class Mod64 {

	private static int mod( int i, int modulus) {
	     int result = i % modulus;
	     return result < 0 ? result + modulus : result;
	}
	
	public static void main(String[] args) {
		final int MODULES = 3;
		int[] histogram = new int[MODULES];

		//int i = Integer.MIN_VALUE;
		short i = Short.MIN_VALUE;
		// abs(Int.min)= Int.min. It < 0.
		do {
		   //histogram[Math.abs(i) % MODULES]++; // java.lang.ArrayIndexOutOfBoundsException: -2
			histogram[mod(i, MODULES)]++;
			System.out.println(i);
		// } while (i++ != Integer.MAX_VALUE );
		} while ( i++ != Short.MAX_VALUE);

		for( int j = 0; j < MODULES; j++)
		     System.out.println("result:" + histogram[j] + " "); // sum = 65536 = 2^ 16
	}
}
