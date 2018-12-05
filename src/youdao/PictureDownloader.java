package youdao;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class PictureDownloader implements Runnable {
	private String mUrl = null;
	private int index = -1;

	public PictureDownloader(DownloadTask task){
		this.mUrl = task.url;
		this.index = task.index;
	}
	
	@Override
	public void run() {
		URL url = null;
		
        try {
            url = new URL(this.mUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  "C:\\Users\\i042416\\Pictures\\clipboard" +
             ++this.index + ".png";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            System.out.println("File " + this.index + " downloaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
