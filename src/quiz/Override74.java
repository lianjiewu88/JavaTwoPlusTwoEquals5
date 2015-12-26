package quiz;

public class Override74 {

	public static void main(String[] args) {
		Enigma e = new Enigma();
		System.out.println(e.equals(e));

	}

}

final class Enigma {
	 // don't do this!
	// e.equals(e)和e.equals((Object)e)将返回不同的结果。
	 public Boolean equals(Enigma other) { // overload
	    return false;
	 }
	}

