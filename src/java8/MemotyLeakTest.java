package java8;

class MemotyLeakTest1 {
	/*
	public static void main(String[] args) {
		Runnable runnable = runnable();
		runnable.run(); // Breakpoint here
	}*/

	static Runnable runnable() {
		return () -> {
			System.out.println("Hello");
		};
	}
}

class MemotyLeakTest {
	public static void main(String[] args) {
		Runnable runnable = new EnterpriseBean().runnable();
		runnable.run(); // Breakpoint here
	}
}



class EnterpriseBean {
	Object[] enterpriseStateObject = new Object[100000000];
	Runnable runnable() {
		return () -> {
			System.out.println("Hello from this: " + this);
		};
	}
}