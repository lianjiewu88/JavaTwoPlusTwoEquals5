package quiz;

public class Shifty {

	public static void main(String[] args) {
		int i = 0;
		while ( -1 << i != 0){
			int a = -1 << i;
		    i++;
			System.out.println("sequence: " + i + " result: " + a);
			if( i > 1000)
				break;
		}
		
		// solution
		int distance = 0;
		for( int val = -1; val != 0; val <<= 1) {
			distance++;
			System.out.println("distance: " + distance + " result: " + val);
		}
		System.out.println(i);

		int max = Integer.MAX_VALUE;
		int result = max + 1;
		System.out.println(result);
	}

}
