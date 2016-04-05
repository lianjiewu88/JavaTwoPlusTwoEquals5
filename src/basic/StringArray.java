package basic;

public class StringArray {

	public static void main(String[] args) {
		String[] a = new String[] { "what's", "up", "doc?" };
		String[] b = {"what's", "up", "doc?"};
		System.out.println(a.equals(b)); // false
	}

}
