package Interview.wenxin;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author WenxinChen
 */

public class MainTest {
	
	public static ConcurrentHashMap<String,Integer> maps = new ConcurrentHashMap<String,Integer>();
	public static boolean udFlag = true;
	public static CountDownLatch countDownLatch;
	public static void main(String[] args) {
	
		TaskQueue queue = new TaskQueue();
		ThreadPool pool = new ThreadPool(queue);
		List<File> files = new ArrayList<File>();
		String path = null;
		String type = ".txt";
		boolean flag ;
		int num = 1;//线程数目
		int size;//文件个数
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入文件夹的绝对路径：");
		path = sc.nextLine();
		System.out.println("请输入线程数：");
		try{
			num = sc.nextInt();		
		}catch(InputMismatchException e){
			System.out.println("输入为非整数！");
			return;
		}

		flag = ReadFile.getFiles(path, type, files);
		if(flag){
			size = files.size();
			//文件数比输入线程数小
			if(size<num){
				System.out.println("文件数目为： "+size+"，所输入线程数较多，将重设置");
				num = size;
			}
			countDownLatch = new CountDownLatch(num);
			
			for(int i =0 ; i<num; i++){
				List<File> fileList = new LinkedList<File>();
				fileList = getFileList(fileList,files,i,size,num);
				//System.out.println(fileList);
				queue.putTask(new CalcTask());
				pool.addWorderThread(fileList,countDownLatch);
			}
			 try  
			 {  
				// 阻塞当前线程，直到倒数计数器倒数到0  
			      countDownLatch.await();  
			 }  
			 catch (InterruptedException e)  
			 {  
			    e.printStackTrace();  
			  }  

			//System.out.println(maps);
			 Iterator it = maps.keySet().iterator();
			 Map tempMap;
			 String key ;
			 int val;
				while(it.hasNext()){					
					key = (String) it.next();
					val = maps.get(key);
					System.out.println(key+" "+val);
				}
		}		
	}
	private static List getFileList(List<File> fileList, List<File> files, int i, int size, int num) {
		// TODO Auto-generated method stub
		int N = size/num;
		int mod = size%num;
		if (i<(num -1)){
	    	for(int j = N*i; j< N*i+N; j++){
	    		fileList.add(files.get(j));	   
	    		//System.out.println(files.get(j).getAbsolutePath());
	    	}
	    }else{
	    	for(int j =N*i; j< N*i+(size/num + size%num); j++){
	    		fileList.add(files.get(j));
	    		//System.out.println(files.get(j).getAbsolutePath());
	    	}
	    }
		return fileList;
	}
	
	private static void doSleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

