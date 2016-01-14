package Interview.duqianyun;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class Reader implements Runnable{
	private final String folderName;
	private final ConcurrentHashMap<String, AtomicLong> wc ;
	private final ExecutorService ec ;
	private final static String DELIMS = " \t\n\r{}()[]:;.,/!@#$%^&|*";
	
	public Reader(String folderName,ExecutorService ec, ConcurrentHashMap<String, AtomicLong> wc){
		this.folderName = folderName;
		this.ec = ec;
		this.wc = wc;    	
	}

	/**
	 * 递归遍历 main point from run
	 * @param directory
	 * @throws IOException
	 */
	private void crawlAndProcess(File directory) throws IOException{
		for(File file : directory.listFiles()){
			if(file.isDirectory()){
				crawlAndProcess(file);
			}else{
				if(file.getName().toLowerCase().endsWith(".txt")){
					analysisFile(file);
				}
			}
		}	
	}	

	// 2016-01-14 15:42PM Jerry : main point
    @Override
	public void run(){
		File f = new File(folderName);
		// 检查有效性
		if(!f.exists()|| !f.isDirectory()){
			System.out.println("input is not a valid directory path");
			System.exit(1);
		}		
		try {
			crawlAndProcess(f);
		} catch (IOException e) {
			e.printStackTrace();
		} 	    	
	}    	

    /**
     * 文本单词统计
     * @param txtFile
     * @throws IOException
     */    
    private void analysisFile(File txtFile) throws IOException{
		String remains = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(txtFile.getPath()));			
			while(true){
				String res = "";
				try {
					res = readBlockFile(reader);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!res.equals("")){
					// 根据分隔符来分割,将最后一个分隔符后面的部分保留到下一次
					int idx = findLastDelim(res);
					String current = remains + res.substring(0,idx);
					remains = res.substring(idx);	
					ec.submit(new ThreadCount(current,wc));
				}
				else{ // means it the last part of this file
					if(!remains.equals("")){
						ec.submit(new ThreadCount(remains,wc));					
					}
					break;
				}					
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}   
		reader.close();
    }

	/**
	 * ���ҵ�ǰ�ַ�����Ч�ָ��������Բ��������ʵ�ͳ��, ����������ڷָ�����öο���ǰ�����ڱ��жϵĲ���
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
	 * ����һ���ļ��飬����һ���ַ���
	 * @param reader 
	 * @param partitionSize �ļ���Ƭ����
	 * @return �ַ���
	 * @throws IOException
	 */
	private static String readBlockFile(BufferedReader reader) throws IOException{

		int blockSize = 512;		
		StringBuffer blockData = new StringBuffer(blockSize);
		char[] buf = new char[blockSize];
		int numRead = 0;// ÿ�ζ����characters����Ŀ
		int totalRead = 0;//��ǰ�ܹ������characters��Ŀ
		while((numRead = reader.read(buf,0,blockSize))!=-1){

			String readData = String.valueOf(buf,0,numRead);
			blockData.append(readData);			
			buf = new char[blockSize];		
			totalRead = totalRead + numRead;
			if(totalRead>= blockSize)
				break;
		}
		//���ʿ��ܴ��м�Ͽ�,���Ժ�����Ҫ����
		return blockData.toString();
	}
}
