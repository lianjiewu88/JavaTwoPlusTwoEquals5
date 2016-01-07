package quiz;

class StrungOut67 {
    public static void main(String[] args) { // runtime error, String points to the inner class String
       String s = new String("Hello world");
       System.out.println(s);
       
       int[] input = {1,2,3,4,5,6};
       System.out.println(sum(input));
       System.out.println("new: " + sum2(input,0, input.length - 1));
  }
    
    private static int[] trunc(int[] origin){
    	int length = origin.length;
    	int[] temp = new int[length-1];
    	for( int i = 0; i < length-1; i++ ){
    		temp[i] = origin[i+1];
    	}
    	return temp;
    }
    
    public static int sum(int[] array){
    	if( array.length == 0)
    		return 0;
    	if( 1 == array.length )
    		return array[0];
    	return array[0] + sum(trunc(array));
    }
    
    public static int sum2(int[] array, int start, int end){
    	if( start == end)
    		return array[start];
    	else
    		return array[start] + sum2(array, start+1, end);
    }
}
    
/*
class String {
	private final java.lang.String s;
	public String(java.lang.String s) {
		this.s = s;
	}
	public java.lang.String toString() {
		return s;
	}
}
*/
