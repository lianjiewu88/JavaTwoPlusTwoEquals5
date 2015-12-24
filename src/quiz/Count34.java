package quiz;

public class Count34 {

	public static void main(String[] args) {
		final int START = 2000000000;
		int count = 0;
		for( float f = START; f < START + 50; f++)
		    count++;
		System.out.println(count);
		float a1 = 2000000000;
		float a2 = 2000000050;
		System.out.println(a1); // 2.0E9
		System.out.println(a2); // 2.0E9
		float a3 = a1 - a2;
		System.out.println(a3); // 0.0 50 is lost!
		System.out.println("Max expoent: " + Float.MAX_EXPONENT);
		System.out.println("Max value: " + Float.MAX_VALUE);
		System.out.println("number of bits to hold a float: " + Float.SIZE);
		// 24位浮点数 111..11 - 一共23个1，十进制为8388607
		float a4 = 8388000;
		float a5 = 8387950;
		System.out.println(a4); // 8388000.0
		System.out.println(a5); // 8388000.0
		float a6 = 9999999;
		System.out.println(a6); // 9999999
		float a7 = 10000001;
		System.out.println(a7);  // 1.0000001E7 从这里开始就不能精确表示了。由于float 32位的design决定。
		
		System.out.println("Hello");
		Thread t = new Thread(()-> { System.out.println("I am shut down!");});
		Runtime.getRuntime().addShutdownHook(t);
		System.exit(-1); // 不会使finally得到调用。
	}

}
