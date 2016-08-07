package performance;

public class LongTest {
	// 下面的代码执行，在我们的机器上需要 9 秒，但当我把 Long 改成 long 之后，0 秒内就完成了）
	public static void main (String[] args) {
	 long millis = System.currentTimeMillis ();
	    long sum = 0L; // uses Long, not long
	    for (long i = 0; i <= Integer.MAX_VALUE; i++) {
	        sum += i;
	    }
	    System.out.println (sum);
	    System.out.println ((System.currentTimeMillis () - millis) / 1000);}
}
