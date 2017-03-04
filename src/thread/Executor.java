package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class JerryThread implements Runnable{

	@Override
	public void run() {
		 while(true){
             System.out.println("begincode");
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
	}
	
}
public class Executor {

	private AtomicInteger atomicI = new AtomicInteger(0);
	
	private static void launch(){
		
		//定义了线程池中最大存在的线程数目
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        //添加了一个任务...
        executorService.execute(new JerryThread());
	}
	public static void main(String[] args) {
		launch();
	}

}
