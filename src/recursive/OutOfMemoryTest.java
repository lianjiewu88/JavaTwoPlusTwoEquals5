package recursive;

// a good blog: http://epy.iteye.com/blog/1914455

public class OutOfMemoryTest {

	public static void calc(int arg1, int arg2){
		int _arg1 = arg1;
		int _arg2 = arg2;
		//System.out.println(arg1);
		calc( arg1 + arg2 , arg2);
		
	}
	public static void main(String[] args) {
		long arr[];      
	    for (int i=1; i<=10000000; i*=2) {  
	        arr = new long[i];  
	    }  
		// calc(1,2);
	}

}
