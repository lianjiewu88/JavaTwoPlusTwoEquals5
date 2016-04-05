package Interview.wenxin;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.Iterator;
import java.util.List;

public class WorderThread extends Thread {
	private static int count = 0;
	private boolean busy = false;
	private boolean stop = false;
	private CountDownLatch cdl;
	private TaskQueue queue;
	private List<File> fileList;
	private ConcurrentHashMap<String,Integer> hMap,maps ;
	//private static ReadWriteLock lock = new ReentrantReadWriteLock();
	private static Object lockObj = new Object();
	public WorderThread(ThreadGroup group, TaskQueue queue, List<File> fileList,CountDownLatch cdl){
		super(group,"worker-"+count);
		count++;
		this.queue = queue;
		this.fileList = fileList;
		this.cdl = cdl;
		//this.maps = (TreeMap<String, Integer>) maps;
	}
	public void shutDown(){
		stop = true;
		this.interrupt();
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public  boolean isIdle(){
		return !busy;
	}
	
	public void run(){

		maps = (ConcurrentHashMap<String, Integer>) MainTest.maps;
		while(!stop){
			Task task = queue.getTask();
			if (task!= null){
				busy = true;
				for(Iterator<File> it = this.fileList.iterator();it.hasNext();){
					File file = (File)it.next();
					hMap = task.execute(file);
					synchronized (WorderThread.lockObj) {
					Iterator it0 = maps.keySet().iterator();
					while(it0.hasNext()){
						String key1 = (String) it0.next();//maps��keyֵ
						if(hMap.containsKey(key1)){
							int v1 = maps.get(key1);
							int v2 = hMap.get(key1);
							maps.put(key1, v1+v2);
							hMap.remove(key1);
						}
					
					}
					if(hMap != null){
						maps.putAll(hMap);
						hMap.clear();
					}
					}
				}
				busy = false;
				cdl.countDown();
				return;
			}
			
		}
			
	}
}
