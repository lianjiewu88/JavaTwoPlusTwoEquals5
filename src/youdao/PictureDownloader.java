package youdao;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class PictureDownloader implements Runnable {
	private String mUrl = null;

	public PictureDownloader(String url){
		this.mUrl = url;
	}
	
	@Override
	public void run() {
		URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(this.mUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  "F:/test.jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
