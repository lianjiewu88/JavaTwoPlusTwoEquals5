package constant;

public class ConstantFolding {

	static final  int number1 = 5;
	 
    static final  int number2 = 6;
 
    static int number3 = 5;
 
    static int number4= 6;

	public static void main(String[] args) {
		 int product1 = number1 * number2;         //line A
         int product2 = number3 * number4;         //line B

	}

}
