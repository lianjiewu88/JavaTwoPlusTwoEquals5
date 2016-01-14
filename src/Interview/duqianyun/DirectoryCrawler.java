package Interview.duqianyun;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DirectoryCrawler {

	private  ExecutorService workingPool;
	private  ExecutorService readerPool ;
	private  ConcurrentHashMap<String, AtomicLong> wc;

	public DirectoryCrawler(){
		this(1);
	}

	public DirectoryCrawler(int nThreads){
		readerPool  = Executors.newSingleThreadExecutor();
		workingPool = Executors.newFixedThreadPool(nThreads);
		wc = new ConcurrentHashMap<String, AtomicLong>();
	}

	private void process(String folderName) throws IOException{

		readerPool.execute(new Reader(folderName,workingPool, wc));				
		readerPool.shutdown();
		try{
			readerPool.awaitTermination(20, TimeUnit.MINUTES);
		}catch(InterruptedException e){
			System.out.println("ReaderPool termination Interrupted");
			System.exit(1);
		}	
		workingPool.shutdown();
		try{
			workingPool.awaitTermination(20, TimeUnit.MINUTES);
		}catch(InterruptedException e){
			System.out.println("workingPool termination Interrupted");
			System.exit(1);
		}	
	}	

	public void printResults(){
		if(wc.isEmpty())
			return;
		for(Map.Entry<String, AtomicLong> e : wc.entrySet()){
			System.out.println(e.getKey()+": "+e.getValue());
		}	
	}	

	public static void main(String[]args) throws IOException{
		long startTime = System.currentTimeMillis();
		if(args.length<1){
			System.out.println("Usage:<directoryPath> [<numOfThreads>]");
			System.exit(1);
		}
		DirectoryCrawler dirCrawler = null;
		if(args.length==1){
			dirCrawler = new DirectoryCrawler();//����Ĭ�ϲ���
		}else{
			dirCrawler = new DirectoryCrawler(Integer.valueOf(args[1]));
		}
		
		// main method
		dirCrawler.process(args[0]);	
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;	
		dirCrawler.printResults();
		System.out.println("Total time = "+elapsed+" ms");

	}



}
