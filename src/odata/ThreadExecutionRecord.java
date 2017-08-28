package odata;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadExecutionRecord {
	private long start;
	private long end;
	public final static String THREADPREFIX = "JERRYTHREAD";
	
	private static ConcurrentHashMap<String, ThreadExecutionRecord> mRecord = 
			new ConcurrentHashMap<String, ThreadExecutionRecord>();
	
	private ThreadExecutionRecord(String name){
		this.start = System.currentTimeMillis();
	}
	
	static public void insertRecord(String name){
		ThreadExecutionRecord record = new ThreadExecutionRecord(name);
		mRecord.put(name, record);
	}
	
	static public void recordEndTimestamp(String threadName){
		ThreadExecutionRecord record = mRecord.get(threadName);
		assert record != null;
		record.end = System.currentTimeMillis();
		mRecord.put(threadName, record);
	}
	
	static public void log(){

		Iterator<String> it = mRecord.keySet().iterator();

		long total = 0;
		while(it.hasNext()){
			String key = it.next();
			long response = mRecord.get(key).end - mRecord.get(key).start;
			total += response;
			System.out.println("Thread: " + key + " Response Time: " + response);
		}
		System.out.println("Average ResponseTime: " + total / mRecord.size());
	}
}
