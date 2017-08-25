package odata;


public class CustomerQueryTest {


	private void test() {
		Thread thread = new Thread( new QueryRunner());
		thread.start();
	}

	public static void main(String[] args) {
		new CustomerQueryTest().test();
	}

}
