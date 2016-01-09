package stringTest;

public class stringTest {

	public static void main(String[] args) {
		String userName = "Jerry";
		String skill = "JS";
		String job = "Developer";
		String info = userName + skill + job;
		System.out.println(info);
		reverse("Jerry");
	}
	
	public static String reverse(String s) {
	    int N = s.length();
	    char[] a = new char[N];
	    for (int i = 0; i < N; i++)
	       a[i] = s.charAt(N-i-1);
	    String reverse = new String(a);
	    System.out.println("after reverse: " + reverse);
	    return reverse;
	}
	
	

}
