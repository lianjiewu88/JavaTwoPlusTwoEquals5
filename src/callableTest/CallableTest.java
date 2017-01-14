package callableTest;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableIndicator{
	private String name;
	private Double result;
	
	public CallableIndicator(String name, Double result){
		this.name = name;
		this.result = result;
	}
	
	public Double getResult(){
		return this.result;
	}
	
	public String getName(){
		return this.name;
	}
}

public class CallableTest {
	 private static final int POOL_SIZE = 10;
	 
	    static class CalcThread implements Callable<CallableIndicator> {
	    	
	    	private String name;
	        private List<Double> dataList = new ArrayList<>();
	 
	        public CalcThread(String name) {
	            for(int i = 0; i < 10000; ++i) {
	                dataList.add(Math.random());
	            }
	            this.name = name;
	        }
	 
	        @Override
	        public CallableIndicator call() throws Exception {
	        	
	            double total = 0;
	            for(Double d : dataList) {
	                total += d;
	            }
	            return new CallableIndicator(this.name, total / dataList.size());
	        }
	 
	    }
	 
	    public static void main(String[] args) {
	        List<Future<CallableIndicator>> fList = new ArrayList<>();
	        ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE);
	        for(int i = 0; i < POOL_SIZE; ++i) {
	            fList.add(es.submit(new CalcThread("Jerry Thread: " + i)));
	        }
	 
	        for(Future<CallableIndicator> f : fList) {
	            try {
	                System.out.println("Thread name: " + f.get().getName() + " result: " +  f.get().getResult());
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	 
	        es.shutdown();
	    }
}
