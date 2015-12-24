package quiz;

public class BigDelight24 {

	public static void main(String[] args) {
		
		byte c = Byte.MAX_VALUE;
		System.out.println("max: " + c);
		c++;
		System.out.println("max: " + c);
		
		byte convert = (byte)0x90; // -112
		System.out.println(convert);
		// 如果改成<=, 就无限循环了。
		for( byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
			
		     if ( b == (byte)0x90 )
		       System.out.println("WWWWWWWWWWWW Job!");
		     // b 和0xff的结果一定是一个int
		     
		     int temp = ( b & 0xff ); // cannot convert from int to byte
		     System.out.println("before: " + b + " after: " + temp);
		}
		System.out.println("end");
		int j = 0;
		for( int i = 0; i < 10; i++){
			j = j++;
			/*  相当于
			 * int temp = j;
			 * j = j + 1;
			 * j = temp;
			 */
		}
		System.out.println("after j: " + j);
	}

}
 