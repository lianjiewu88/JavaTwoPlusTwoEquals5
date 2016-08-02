package basic;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class FloatTest {
	public static String A = "JerryTest";
	public static void main(String[] args) {
	 float a = (float) 1.03;
	 float b = (float) .42;
	         
	 double c = 1.03;
	 double d = .42;
	 System.out.println(a * b);
	 System.out.println(c - d);
	 /* 0.43259996

	0.6100000000000001
	  */
	 
	 BigDecimal a1 = new BigDecimal("1.03");
	 BigDecimal b1 = new BigDecimal(".42");
	 System.out.println(a1.multiply(b1));
	 System.out.println(a1.subtract(b1));
	 
	 FloatTest tool = new FloatTest();
	 System.out.println(tool.A);
	 Object classObject = FloatTest.class;
	 
	 Class<FloatTest> convert = (Class<FloatTest>)classObject;
	 Field[] fields = convert.getFields();
	 Object classObject2 = FloatTest.class.getClass();
	 Field staticField = fields[0];
	 try {
		System.out.println("static field value: " + staticField.get(tool));
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
}
