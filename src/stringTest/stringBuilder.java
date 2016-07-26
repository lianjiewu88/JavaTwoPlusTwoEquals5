package stringTest;

public class stringBuilder {

	public static void main(String[] args) {
		StringBuilder result = new StringBuilder();
		String values[] = {"1","2","3"};
		for(int i = 0; i < values.length;i++){
			result.append(values[i]);
		}
		
		result.insert( 1, "a");
		System.out.println(result.toString());
		result.insert( 2, "b" + " ");
		System.out.println(result.toString());
	}
}
