package stringTest;

public class stringTestLoop {

	public static void main(String[] args) {
		 String result = "";
		 String values[] = {"1","2","3"};
 		 for(int i = 0;i < values.length;i++){
		     result += values[i];
		 }
		 System.out.println(result);
	}
}
