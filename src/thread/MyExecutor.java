package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class MyThread implements Runnable{

	private List<Integer> myList;
	private Object host;
	public MyThread(List<Integer> list, Object object){
		this.myList = list;
		this.host = object;
	}
	
	public void updateListSafe(){
		synchronized(this.host){
			ArrayList<Integer> safe = new ArrayList<Integer>();
			safe.add(1);
		}
	}
	
	private void updateList(int i){
		synchronized(this.host){
			myList.add(i);
		}
	}

	@Override
	public void run() {
		while(true){
			updateList(1);
		}
	}
	
}
public class MyExecutor {

	private ArrayList<Integer> taskList = new ArrayList<Integer>();
	private Object object = new Object();
	private void launch(){
		
        ExecutorService executorService= Executors.newFixedThreadPool(10);

        executorService.execute(new MyThread(taskList, object));
        executorService.execute(new MyThread(taskList, object));
	}
	public static void main(String[] args) {
		MyExecutor test = new MyExecutor();
		test.launch();
	}

}
