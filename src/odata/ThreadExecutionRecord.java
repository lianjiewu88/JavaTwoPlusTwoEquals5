package odata;

public class ThreadExecutionRecord {
	private String threadName;
	private long start;
	private long end;
	
	public ThreadExecutionRecord(String name, long start, long end){
		this.threadName = name;
		this.start = start;
		this.end = end;
	}
	
	
}
