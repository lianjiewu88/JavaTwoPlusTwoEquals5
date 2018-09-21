package flick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Merger {

	private static void run() throws IOException{
		
		FileInputStream in = null;
		String destFile = DownloadLauncher.LOCALPATH + 
				DownloadLauncher.MERGED;
		
		FileOutputStream out = new FileOutputStream(destFile,true);
		
		for( int i = 0; i <= DownloadLauncher.LAST; i++){
			byte[] buf = new byte[1024];
			int len = 0;
			String sourceFile = DownloadLauncher.LOCALPATH + i + 
					DownloadLauncher.POSTFIX;
			
			in = new FileInputStream(sourceFile);
	
			while( (len = in.read(buf)) != -1 ){
				out.write(buf,0,len);
			}
		}
		out.close();
	}
	
	public static void main(String[] args) {
		try {
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Merged ok!");
	}

}
