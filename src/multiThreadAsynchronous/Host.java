package multiThreadAsynchronous;



/*
 *  host("蛋糕店")在接到请求后，先生成了“提货单”FutureData的实例future，
 *  然后命令“蛋糕师傅”RealData去做蛋糕，realdata相当于起个线程去做蛋糕了。
 *  然后host返回给顾客的仅仅是“提货单”future，而不是蛋糕。当蛋糕做好后，
 *  蛋糕师傅才能给对应的“提货单”蛋糕，也就是future.setRealData(realdata)。
 */
public class Host {  
	  public Data request(final int count, final char c) {  
	    System.out.println("request(" + count + ", " + c + ") BEGIN");  
	    // (1) 建立FutureData的实体  
	    final FutureData future = new FutureData();  
	    // (2) 为了建立RealData的实体，启动新的线程  
	    new Thread() {                     
	      public void run() {  
	       //在匿名内部类中使用count、future、c。            
	        RealData realdata = new RealData(count, c);  
	        future.setRealData(realdata);  
	      }                         
	    }.start();                       
	    System.out.println("request(" + count + ", " + c + ") END");  
	    // (3) 取回FutureData实体，作为传回值  
	    return future;  
	  }  
	}