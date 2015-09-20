package bigFileZ;

public interface Consts {
	
	/* 
	 * 2015.9.11修改： 1. 文件读取使用异步IO实现； 2. 单词统计使用stream的API实现；

实现思路：将目标文件分段读取到缓冲区，便读取边统计，将单词和出现次数用hashmap存取计算。

性能优化点: 1. 读取超过1GB的大文件，使用内存映射文件方式分段读取，每个分段分配一个线程处理。由于IO有瓶颈，分段读取的线程到达一定数量性能就不再提升，需要根据实际运行环境调节。 2. 每段文件按照设定的buffer依次读取，然后创建处理任务提交到线程池中处理，可以提升处理效率；
	 */
	/**读取文件数据的缓冲区大小*/
	int READ_BUFFER_SIZE = 1024 * 10;
	
	/**处理文件读取的线程数*/
	int PROCESS_FILE_THREAD_NUM = 10;
	
	/**处理单词统计的线程数*/
	int PROCESS_STAT_THREAD_NUM = 20;
}
