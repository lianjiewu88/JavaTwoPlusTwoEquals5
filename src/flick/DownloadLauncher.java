package flick;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadLauncher {

	static final public int LAST = 109;
	static final public int START = 1;
	static final public String PREFIX = "https://cfvod.kaltura.com/scf/hls/p/1921661/sp/192166100/serveFlavor/entryId/1_e4vktn7o/v/1/ev/7/flavorId/1_51i2iaqb/name/a.mp4/seg-";


	static final public String POSTFIX1 = "-v1-a1.ts?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9jZnZvZC5rYWx0dXJhLmNvbS9zY2YvaGxzL3AvMTkyMTY2MS9zcC8xOTIxNjYxMDAvc2VydmVGbGF2b3IvZW50cnlJZC8xX2U0dmt0bjdvL3YvMS9ldi83L2ZsYXZvcklkLzFfNTFpMmlhcWIvbmFtZS9hLm1wNC8qIiwiQ29uZGl0aW9uIjp7IkRhdGVMZXNzVGhhbiI6eyJBV1M6RXBvY2hUaW1lIjoxNTQ0NTExMjI4fX19XX0_&Signature=ZvSmUm7fUqvB7mX2XyrsMozNlRDL7O0QDkKTG5te-NDPNPvc1y7m~q20lAFYk2t0UzCOHSdpF5Jms3B65QPXG2uOF94eBQ8QnNAsUcCDyeFScwLo2b1WNXPnjn18cSZYXH2sBf9TiCu8TrMTWlf6SYGnhtWxQQfzdjbyVWhC9Lpt0uDL8gE~xpxNRUADRIJL7wQru4eBPWbqUFNkP6nNeTTkfcH~y-cYR1kZW8IaYoh1JRLTnZUrMjQMbyC6zi4MExo-SmAErLBXrjmjgMJ1fK4f9yRzIqlXnwIfg60jPDP-pBCvIGtVV22F0SLisaBrfHRCjSuOnZ0r0NvtcYJLZQ__&Key-Pair-Id=APKAJT6QIWSKVYK3V34A";
	static final public String POSTFIX = ".ts";
	static final public String LOCALPATH = "c:\\temp\\";
	static final public String MERGED = "merged.avi";
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		for( int i = START; i <= LAST; i++ ){
			// String task = PREFIX + i + POSTFIX;
			String task = PREFIX + i + POSTFIX1;
			VideoFragmentDownloader load = new VideoFragmentDownloader(task, i);
		    executor.execute(load);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
        }

	}

}
