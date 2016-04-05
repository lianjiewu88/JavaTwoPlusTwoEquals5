package Interview.wenxin;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadPool extends ThreadGroup {
	// public static List threads = new LinkedList();
	private TaskQueue queue;
	// private List<File> fileList;
	public ThreadPool(TaskQueue queue){
		super("thread-Pool");
		this.queue = queue;
	}
	public synchronized void addWorderThread(List<File> fileList,CountDownLatch cdl){
		Thread t = new WorderThread(this,queue,fileList,cdl);
		// threads.add(t);			
		t.start();
	}
	
}
