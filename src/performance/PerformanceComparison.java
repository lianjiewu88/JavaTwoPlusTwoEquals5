package performance;

public class PerformanceComparison {

	static public final long total = 10000000000l; // 10 billons
	
	private long startTime;
	private long memberAdd = 0l;
	private void start(){
		this.startTime = System.currentTimeMillis ();
	}
	
	private long getMember(){
		return this.memberAdd;
	}
	private long end(){
		return System.currentTimeMillis () - this.startTime;
	}
	
	public void test(){
		this.pureAdd();
		this.memberAdd();
		this.memberAdd = 0;
		this.methodAdd();
	}
	
	private void pureAdd(){
		this.start();
		long total = this.total;
		long add = 0;
		for( long i = 0; i < total; i++){
			add++;
		}
		System.out.println("Pure Add: " + this.end() + " result: " + add);
	}
	
	private void memberAdd(){
		this.start();
		long total = this.total;
		for( long i = 0; i < total; i++){
			this.memberAdd++;
		}
		System.out.println("Member Add: " + this.end() + " result: " + this.memberAdd);
	}
	
	private void methodAdd(){
		this.start();
		long total = this.total;
		for( long i = 0; i < total; i++){
			this.memberAdd = this.getMember() + 1;
		}
		System.out.println("Method Add: " + this.end() + " result: " + this.memberAdd);
	}
	
	public static void main(String[] args) {
		PerformanceComparison tool = new PerformanceComparison();
		tool.test();
	}

}
