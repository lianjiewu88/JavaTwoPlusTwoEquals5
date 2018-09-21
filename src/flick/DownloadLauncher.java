package flick;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadLauncher {

	static final public int LAST = 56;
	static final public String PREFIX = "http://d2vvqvds83fsd.cloudfront.net/vin02/vsmedia/_definst_/smil:event/18/36/06/3/rt/1/resources/180919_PID_Intelligent_Enterprise_Gruenewald_720p-5F92.smil/media_b433000_";
	static final public String POSTFIX = ".ts";
	static final public String LOCALPATH = "c:\\temp\\";
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		for( int i = 0; i <= LAST; i++ ){
			String task = PREFIX + i + POSTFIX;
			VideoFragmentDownloader load = new VideoFragmentDownloader(task, i);
		    executor.execute(load);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
        }

	}

}
