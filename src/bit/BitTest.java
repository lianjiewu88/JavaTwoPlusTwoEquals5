package bit;

public class BitTest {

	private static int add(int number){
		number = number + 1;
		return number;
	}
	public static void test1() {
		int mask;
		for(int i = 0; i < 32; i++) {
		    mask = 1<< i; 
		    System.out.println(mask); // 打印2的n次方 1073741824, -2147483648
		}
	}
	
	public static void test2() {
		/* 1: 0001 
		 * 2: 0010 
		 * 3: 0011
		 * 4: 0100
		 * 5: 0101
		 * 6: 0110
		 * 7: 0111
		 * 8: 1000 // 2的n次幂的特点是第N位为1
		 * 9: 1001
		 * 10:1010
		 * 3 & 5 = 0001 = 1
		 * 6 & 7 = 0110 = 6
		 * 3 | 5 = 0111 = 7
		 * 3 ^ 5 = 0110 = 6
		 * */
		int c = 3 & 5;
		System.out.println(c);
		int d = 6 & 7;
		System.out.println(d);
		
		d = 3 | 5;
		System.out.println(d);
		d = 3 ^ 5;
		System.out.println(d);
	}
	
	// all other occurance: 2, only one number occurance: 1
	public static int twiceNumber(int[] A) {
	    int total = 0;
	    for(int a : A)
	        total ^= a;
	    return total;
	}
	
	public static int ThirdNumber(int[] A) {
	    int ret = 0;
	    for(int i = 0; i < 32; i++) {
	        int c = 0, mask = 1 << i;  //  mask, 第i位为1，其他位都为0
	        for(int j = 0; j < A.length; j++) {
	        	int val = (A[j] & mask);
	        	if( val > 0 || val < 0) {  // ② 如果该数在这一位上为1，计数器就加一
	        		c++;
	        	}
	        }
	        System.out.println("Digit index: " + i + " Mask: " + mask + " Occurance: " + c);
	        if(c%3 > 0) { // ③ 这一位的计数除以3取余数，在这里只可能为0或1 
	        	System.out.println("This digit is hit!: " + i);
	            ret |= mask;
	    	}
	    }
	    return ret;
	}
	
	public static int test4(int[] nums) {
		if(nums==null || nums.length == 0){
			return Integer.MIN_VALUE;
		}
		
		int []result = new int[32];
		int ans = 0;
		for(int i=0; i<32; i++){
			for(int j=0; j<nums.length; j++){
				result[i] += ((nums[j]>>i)&0x01);
			}
			ans |= ((result[i]%3)<<i);
		}
		
		return ans;
	}
	
	public static int test3(int[] A) {
		int ones = 0, twos = 0;
		for(int i = 0; i < A.length; i++){
		        ones = (ones ^ A[i]) & ~twos;
		        twos = (twos ^ A[i]) & ~ones;
		}
		return ones;
	}
	
	public static int compare(int a, int b){
		
		int diff = a ^ b;
		if( diff == 0)
			return 0;
		diff = diff | ( diff >> 1 );
		  diff |= diff >> 2;
		  diff |= diff >> 4;
		  diff |= diff >> 8;
		  diff |= diff >> 16;
		  diff ^= diff >> 1;
		  return  ( (a & diff) == 0 )  ? -1 : 1;
	}
	
	private static void unitTest(){
		System.out.println(compare(1,2));
		System.out.println(compare(3,2));
		System.out.println(compare(300,2));
		System.out.println(compare(3000,2));
		System.out.println(compare(3000,3000));
		System.out.println(compare(3000,3001));
	}
	
	public static void main(String[] args) {
		
		System.out.println("Add: " + add(6));
		// int a[] = {1,1,2,2,5,4,4};
		// System.out.println("Twice: " + twiceNumber(a));
		// int a[] = {1,999,1,1,2,2,2,4,4,4,6,6,6};
		// int index = ThirdNumber(a);
		// System.out.println("Index: " + index); // 5 , 第0位和第2位为1，所以为5
		/*
		System.out.println("Third: " + a[index]);
		
		index = test3(a);
		System.out.println("Index: " + index);
		System.out.println("Third: " + a[index]); */
		//System.out.println("fourth:" + test4(a));
		
		unitTest();
		
	}
}
