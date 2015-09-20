package bigFileZ;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WordStat {

	/** 执行统计任务的线程池 */
	private ExecutorService statThrdPool = Executors.newFixedThreadPool(Consts.PROCESS_STAT_THREAD_NUM);

	/** 存放统计数据 */
	private Map<String, Long> map = new ConcurrentHashMap<>();

	/** 存放空格分段后的第二段数据， 与下批次读取的数据拼接，避免截断单词*/
	private String cacheString = "";	
	
	/**
	 * 初始化一个文件统计
	 * 
	 * @param file
	 */
	public void init(File file) {
		ExecutorService fileThrdPool = Executors.newFixedThreadPool(Consts.PROCESS_FILE_THREAD_NUM);		
		Set<OpenOption> options = new TreeSet<>();
		options.add(StandardOpenOption.READ);		
		List<Future<ByteBuffer>> futures = new ArrayList<>();		
		long pos = 0;
		try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(file.toURI()), options , fileThrdPool)) {  
            while (pos < file.length()) { 
            	final long currPos = pos;
            	futures.add(fileThrdPool.submit(() -> {
                	ByteBuffer buffer = ByteBuffer.allocateDirect(Consts.READ_BUFFER_SIZE);                  	            
                	afc.read(buffer, currPos);
                    return buffer;                   
                }));
                pos += Consts.READ_BUFFER_SIZE;
            }
            fileThrdPool.shutdown();            
            for (Future<ByteBuffer> future : futures) {                              
                handle(future.get()); 
            }
            /**将最后的分段数据提交统计处理*/
            if (cacheString.length() > 0) {
            	handle(ByteBuffer.wrap(cacheString.getBytes()));
            }
            statThrdPool.shutdown();  
            statThrdPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (Exception e) {  
            e.printStackTrace();
        }
	}	
	
	/**
	 * 查询指定单词的统计次数
	 * 
	 * @param word
	 * @return
	 */
	public long queryWord(String word) {
		return map.containsKey(word) ? map.get(word) : 0;
	}

	/**
	 * 统计传入数据中出现的单词数量
	 * 
	 * @param buffer
	 */
	private void handle(ByteBuffer buffer) {
		buffer.flip();
		String data = Charset.forName("utf-8").decode(buffer).toString();
		buffer.clear();
		/**用空格将数据分成两段*/
		String[] splitStr = data.split(" ", 2);	
		/**与上一次缓存的数据拼接*/
		splitStr[0] = cacheString + splitStr[0];
		cacheString = splitStr.length == 2 ? splitStr[1] : "";		 
		statThrdPool.execute(() -> {	
			Map<String, Long> tmpMap = Arrays.asList(splitStr[0].split("\\W+")).parallelStream().filter(w -> w.length() > 0).collect(groupingBy(w -> w, counting()));			
			for (Entry<String, Long> entry : tmpMap.entrySet()) {
				map.merge(entry.getKey(), entry.getValue(),  (value, newValue) -> value+newValue);
			}			
		});			
	}	
}
