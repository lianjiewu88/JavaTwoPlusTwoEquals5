package recursive;

public class FactorialCalc {

	private static int factorial(int n, int result) {
		   if ( n == 1 )
		        return result;
		   else
		        return factorial(n-1, n*result);
	}
	
	public static void main(String[] args) {
		int result = factorial(5, 1);
		System.out.println("result: " + result);
	}

}
