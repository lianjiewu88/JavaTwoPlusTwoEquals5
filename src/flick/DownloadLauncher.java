package flick;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadLauncher {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		String task = "http://d2vvqvds83fsd.cloudfront.net/vin02/vsmedia/_definst_/smil:event/18/36/06/3/rt/1/resources/180919_PID_Intelligent_Enterprise_Gruenewald_720p-5F92.smil/media_b433000_1.ts"; 
		VideoFragmentDownloader load = new VideoFragmentDownloader(task);
	    executor.execute(load);
		
		executor.shutdown();
		while (!executor.isTerminated()) {
        }

	}

}
