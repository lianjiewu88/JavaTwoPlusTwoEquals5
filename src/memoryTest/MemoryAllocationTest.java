package memoryTest;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryAllocationTest {
	static public void main(String[] arg){
		    Timer timer = new Timer();
		    TimerTask task = new TimerTask() {
		        @Override
		        public void run() {
		            Runtime runtime = Runtime.getRuntime();
		            System.out.print("total:"+(runtime.totalMemory()/1024)+ "k\n");
		            long free=runtime.freeMemory()/1024;
		            System.out.print("free:" + free+ "k\n");
		            if(free<102400){
		                System.out.print("need gc"+"\n");
		            }
		            byte[] a1 = new byte[100 * 1024 * 1024];
		            a1[1] = 1;
		            System.out.print(a1[1]+"\n");
		        }
		    };
		    timer.schedule(task, 1000, 1000);
		}
	}
