package quiz;

import java.io.IOException;
import java.io.InputStream;

public class BeerBlast82 {
	   static final String COMMAND = "java BeerBlast82 slave";
	   
	   static void drainInBackground( final InputStream is) {
		     new Thread(new Runnable(){
		        public void run(){
		           try{
		                while( is.read() >= 0);
		           } catch(IOException e){
		               // return on IOException
		           }
		         }
		       }).start();
		     }
	   
	   public static void main(String[] args) throws Exception{
	      if ( args.length == 1 && args[0].equals("slave")) {
	          for ( int i = 99; i > 0; i--){
	             System.out.println( i + " bottles of beer on the wall");
	             System.out.println( i + " bottles of beer ");
		     System.out.println( "You take on down, pass it around, ");
	             System.out.println( (i-1) + " bottles of beer on the wall ");
	             System.out.println();
	        }
	          
	     }/* 启动一个slave进程来打印，但看不到slave进程的输出。主进程等待slave进程结束，然后打印出slave进程的退出值。
	     */ 
	      else {
	          // Master
	          Process process = Runtime.getRuntime().exec(COMMAND);
	          // exit value = 1
	          /* 由于某些本地平台只提供有限大小的缓冲，所以如果未能迅速读取子进程的输出流，就有可能会导致子

进程的阻塞，甚至是死锁。没有足够的缓冲空间来保存这首冗长的歌谣。为了确保slave进程能够结束

，父进程必须排空它的输出流。而这个输出流从master线程的角度是输入流。因此需要在后台线程中完

成这项工作。
	           */
	          drainInBackground(process.getInputStream());
	          int exitValue = process.waitFor();
	          System.out.println("exit value = " + exitValue);
	       }
	    }
	}
