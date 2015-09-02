package Interview.jiaqi;

// Jerry: unused import
//import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FindWordsImp {
	private Lock mapLock = new ReentrantLock(); 
	static private int mIndex = 0;
	private Lock threadNumLock = new ReentrantLock();
	private HashMap<String,Integer> wordsMap= new HashMap<String,Integer>();
	private int totleThreadNum;
	
	private void utilityPrint(String value) {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss:ms");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		System.out.println("" + retStrFormatNowDate + " -ID: " + Thread.currentThread().getId() + " ------:" + value);	
	}
	public HashMap<String,Integer> GetResult()
	{
		return wordsMap;
	}
	
	public void FindAndPrintWords(String absoluteFolderPath, int threadNum)
	{
		totleThreadNum = threadNum;
		File file = new File(absoluteFolderPath);
		if(!file.exists()) {
			return;
		}
		// is a directory
		utilityPrint("Jerry in FindAndPrintWords.........");
		if(!file.isFile())
		{
			if(totleThreadNum >= 1)
			{
				// Jerry 2015-09-01 1 thread for 1 directory
				Thread thread = new Thread(new CalWordsRunnable(file));
				utilityPrint("Jerry Calling thread.start in FindAndPrintWords...");
				thread.start();
				utilityPrint("Jerry thread.start() now returns in FindAndPrintWords...");
				try {
					utilityPrint("Jerry before Calling thread.join in FindAndPrintWords...");
					thread.join();
					utilityPrint("Jerry after Calling thread.join in FindAndPrintWords...");
				} catch (InterruptedException e) {
					utilityPrint("Interrupted Exception occurred!");
					e.printStackTrace();
				}
 
  				// get lock
				threadNumLock.lock();     
           	 	--totleThreadNum;
		        // release
           	 	threadNumLock.unlock();  
			}
			else
			{
				// is a file
				DoFindFiles(file);
			}
		}
	}
// recursive search file

// Jerry: should be private - called by Thread or FindAndPrintWords
private void DoFindFiles(File file)
{
	// Jerry: main logic for thread handling
	if(!file.isFile())
	{
		File[] childFileList = file.listFiles();
		for(int i=0;i<childFileList.length;i++)
		{
			utilityPrint("Jerry Current thread number: " + totleThreadNum);
			if(totleThreadNum>=1)
			{
				Thread thread = new Thread(new CalWordsRunnable(childFileList[i]));				
				thread.start();
				try {
					thread.join();
					utilityPrint("Jerry: the execution call returns from thread.join()");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				threadNumLock.lock();   
				utilityPrint("Jerry Current thread number: " + totleThreadNum + " ready to decrease thread number");
           	 	--totleThreadNum;
           	 	utilityPrint("Jerry Current thread number: " + totleThreadNum);
           	 	threadNumLock.unlock();  
			}
			else
			{
				DoFindFiles(childFileList[i]);
			}
			
		}	
	}	
	else
	{
		// handle file
		String strFileName = file.getName();
		String prefix=strFileName.substring(strFileName.lastIndexOf(".")+1);
		utilityPrint("Prefix: " + prefix + " Whole path: " + file.getAbsolutePath());
		if(prefix.equals("txt"))
		{
			DoCountWords(file);
		}
	}
}
public void DoCountWords(File file)
{
    String result = "";
         try{
             BufferedReader br = new BufferedReader(new FileReader(file));
             String s = null;
             while((s = br.readLine())!=null){
                 result = result + "\n" +s;
             }
             br.close();    
         }catch(Exception e){
             e.printStackTrace();
         }	
        
         utilityPrint("Jerry ******Thread****** is now working on file: " + file.getAbsolutePath());
         String[ ] words = result.replaceAll( "[^a-zA-Z]+", " " ).trim( ).split( " " ); 

         if ( words.length > 0 ) {             
             for ( String word: words ) { 
                 if(wordsMap.containsKey(word))
                 {
                	int count = Integer.valueOf(wordsMap.get(word));
     				mapLock.lock();     
     				wordsMap.put(word, ++count);
     				mapLock.unlock();  
                 }
                 else
                 {
                	// Jerry 2015-09-01: Bad comment
                	mapLock.lock();     
      				wordsMap.put(word, 1);
      				mapLock.unlock();  
                 }
             } 
         }
         utilityPrint("Jerry ******Thread****** finished work for file: " + file.getAbsolutePath());

}
class CalWordsRunnable implements Runnable
{
	private File findFile;
	public CalWordsRunnable(File file)
	{
		utilityPrint("Jerry!!!!! Thread constructor called for file: " + file.getAbsolutePath());
		findFile = file;
	}
	@Override
	public void run() {
		utilityPrint("Jerry DoFindFiles executed within <<THREAD ENVIRONMENT>>:run: " + findFile.getAbsolutePath());
		FindWordsImp.this.DoFindFiles(findFile);
	}
}
}
