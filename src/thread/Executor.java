package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class JerryThread implements Runnable{

	private List<Integer> myList;
	private AtomicInteger myCounter;
	public JerryThread(List<Integer> list, AtomicInteger counter){
		this.myList = list;
		this.myCounter = counter;
	}
	@Override
	public void run() {
		int newValue = 0;
		 while(newValue < 100){
             
			 newValue = myCounter.incrementAndGet();
             System.out.println("begincode: " + Thread.currentThread().getName() + " new value: " 
            		 + newValue);
             myList.add(newValue);
             try {
                 Thread.sleep(50);
                 
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
	}
	
}
public class Executor {

	private AtomicInteger atomicI = new AtomicInteger(0);
	private ArrayList<Integer> taskList = new ArrayList<Integer>();
	
	private void launch(){
		
        ExecutorService executorService= Executors.newFixedThreadPool(10);

        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
        executorService.execute(new JerryThread(taskList, atomicI));
	}
	public static void main(String[] args) {
		Executor test = new Executor();
		test.launch();
	}

}
