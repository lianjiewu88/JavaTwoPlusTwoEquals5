package flick;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class VideoFragmentDownloader  implements Runnable{
	private String mTask;
	private int mIndex;
	
	public  VideoFragmentDownloader(String url, int index){
		this.mTask = url;
		this.mIndex = index;
	}
	
	private void download(){
		URL task = null;
		String path = DownloadLauncher.LOCALPATH + this.mIndex + 
				DownloadLauncher.POSTFIX;
		String url = this.mTask;
		try {
			task = new URL(url);
			DataInputStream dataInputStream = new DataInputStream(task.openStream());
			 
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
 
            byte[] buffer = new byte[1024];
            int length;
 
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            System.out.println("File: " + this.mIndex + " downloaded ok");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		this.download();
	}

}
