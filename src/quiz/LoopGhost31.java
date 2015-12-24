package quiz;

public class LoopGhost31 {

	public static void main(String[] args) {
		/*
		 * 1073741823
			536870911
			268435455
			134217727
			67108863
			...
		 */
		int i = -1;
		short j = -1;
		while ( j != 0) {
			  j >>>= 1;
				System.out.println(j);
				break;
			}
		System.out.println("end");
		int a = Integer.MIN_VALUE;
		int b = 0 - Integer.MIN_VALUE;
		System.out.println(a);
		System.out.println(b);
		System.out.println("i == -i?" + ( a == b));
	}

}
