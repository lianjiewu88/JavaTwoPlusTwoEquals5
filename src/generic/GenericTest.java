package generic;

public class GenericTest {

	public static <T> T requireNonNull(T obj) {
		  if (obj == null) {
		    throw new NullPointerException();
		  } else {
		    return obj;
		  }
		}
	
	public static void main(String[] args) {
		String a = "a";
		
		try {
		String result = GenericTest.requireNonNull(a);
		System.out.println(result);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		

	}

}
