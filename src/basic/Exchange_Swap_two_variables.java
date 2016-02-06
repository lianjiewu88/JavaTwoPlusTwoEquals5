package basic;

public class Exchange_Swap_two_variables {

	static public void swap(int a, int b){
		a = a + b; b = a - b; a = a - b;
		System.out.println("a: " + a + " b: " + b);
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a: " + a + " b: " + b);
	}
	public static void main(String[] args) {
		swap(1,2);
	}
}
