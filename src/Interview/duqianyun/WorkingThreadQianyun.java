package Interview.duqianyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class WorkingThreadQianyun implements Runnable {

	private final File file;
		
	private final ConcurrentHashMap<String, AtomicLong> wc;
	private final static String DELIMS = " \t\n\r{}()[]:;.,/!@#$%^&|*";
	
	public WorkingThreadQianyun(File file,ConcurrentHashMap<String, AtomicLong> wc){
		this.file = file;
		this.wc = wc;
	}
	
	/**
	 * 查找当前字符串有效分割符，避免对不完整单词的统计, 如果都不等于分割符，该段可能前后都属于被切断的部分
	 * 所有暂不处理，留着和下一段buf一起
	 * @param buf
	 * @return
	 */
	private static int findLastDelim(String buf) {
		for (int i = buf.length() - 1; i>=0; i--) {
			for (int j = 0; j < DELIMS.length(); j++) {
				if (DELIMS.charAt(j) == buf.charAt(i)) return i;
			}
		}
		return 0;
	} 

	/**
	 * 读入一个文件块，返回一个字符串
	 * @param reader 
	 * @param partitionSize 文件分片读入
	 * @return 字符串
	 * @throws IOException
	 */
	private static String readBlockFile(BufferedReader reader) throws IOException{

		int blockSize = 512;		
		StringBuffer blockData = new StringBuffer(blockSize);
		char[] buf = new char[blockSize];
		int numRead = 0;// 每次读入的characters的数目
		int totalRead = 0;//当前总共读入的characters数目
		while((numRead = reader.read(buf,0,blockSize))!=-1){

			String readData = String.valueOf(buf,0,numRead);
			blockData.append(readData);			
			buf = new char[blockSize];		
			totalRead = totalRead + numRead;
			if(totalRead>= blockSize)
				break;
		}
		//单词可能从中间断开,所以后期需要处理
		return blockData.toString();
	}	
	
	@Override
	public void run(){
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file.getPath()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		String remains="";		
		while(true){
			String result = "";
			try {
				result = readBlockFile(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!result.equals("")){
				// 根据分隔符来分割,将最后一个分隔符后面的部分保留到下一次
				int idx = findLastDelim(result);
				String current = remains + result.substring(0,idx);//加上上一次的部分
				remains = result.substring(idx);	
				bufCount(current);				
			}
			else{ // means it the last part of this file
				if(!remains.equals("")){
					bufCount(remains);	
				}
				break;
			}		
		}		
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void bufCount(String buf){
		StringTokenizer st = new StringTokenizer(buf,DELIMS);
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			addFrequency(token);
		}
	}
	
	
	private void addFrequency(String word){
		AtomicLong count = wc.get(word);
		if(count == null){
			AtomicLong newNumber = new AtomicLong(0);
			count = wc.putIfAbsent(word,newNumber);
			if(count == null){
				count = newNumber;
			}
		}
		count.incrementAndGet();		
	}
}

