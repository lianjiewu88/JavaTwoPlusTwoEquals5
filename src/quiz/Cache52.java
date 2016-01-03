package quiz;

public class Cache52 {

	static {
		  initializedIfNecessary();
		}

		private static int sum;

		public static int getSum(){
		   initializedIfNecessary();
		   return sum;
		}

		// Cache类有两个静态初始器，顶端的一个static语句块，以及静态域initialized的初始化。
		// 如果初始化代价较高，或者该域在某些执行中不会被用到，那么惰性初始化适用。
		private static boolean initialized = false;
		private static final int sum1 = computeSum();

		private static int computeSum(){
			int result = 0;
			for( int i = 0; i < 100; i++)
		         result += i;
		    return result;
		}
		public static int getSum1(){
			return sum1;
		}
		private static void initializedIfNecessary() {
		   if( !initialized){
		      for( int i = 0; i < 100; i++)
		         sum += i;
		      initialized = true;
		   }
		 }

	public static void main(String[] args) {
		System.out.println(getSum());
		System.out.println(getSum1());
	}
}
