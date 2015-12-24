package quiz;

public class InfiniteLoop {

	public static void main(String[] args) {
		// int i = 1;
		// int i = 1 / 0; // java.lang.ArithmeticException: / by zero
		double j = Double.POSITIVE_INFINITY;
		// double i = 1.0 / 0.0; - no exception
		double i = 1e40;
		while ( i == i + 1) {
			System.out.println("infinite!");
			break;
		}
		double i1 = 1;
		System.out.println(Math.ulp(i1)); // 2.220446049250313E-16
		double i2 = 33554433;
		System.out.println(Math.ulp(i2));
		i2 = 1.0 / 0.0;
		while ( i2 != i2) {
			System.out.println(i2 == i2);
		}
		System.out.println(i2 == i2); // true
		System.out.println(i2 != i2); // false
		System.out.println(i2);

	}

}
