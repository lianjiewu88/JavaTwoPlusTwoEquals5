package quiz;

class StrungOut67 {
    public static void main(String[] args) { // runtime error, String points to the inner class String
       String s = new String("Hello world");
       System.out.println(s);
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
