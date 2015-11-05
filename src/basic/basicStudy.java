package basic;

public class basicStudy {

	public static boolean oddOrNot(int num) {
		return (num & 1) != 0; // return (num & 0x1) != 0;
		}
	
	private static void oddTest(){
		System.out.println(oddOrNot(1));
		
		System.out.println(oddOrNot(2));
		
		System.out.println(oddOrNot(3));
		
		System.out.println(oddOrNot(-1));
		
		System.out.println(oddOrNot(-4));
	}
	
	/* 看起来这段代码会返回"HaHa",但实际返回的是Ha169。原因就是用了双引号的时候，
	 * 字符会被当作字符串处理，而如果是单引号的话，字符值会通过一个叫做基础类型拓宽的操作来转换成整型值。
	 * 然后再将值相加得到169。
	 * */
	 
	private static void quote() {
	      System.out.println("H" + "a" );
	      System.out.println('H' + 'a');
	}
	
	public static void main(String[] args) {
		//oddTest();
		quote();
	}
}
