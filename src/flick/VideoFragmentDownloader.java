package flick;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class VideoFragmentDownloader {

	private static void download(String url){
		URL task = null;
		String path = "C:\\temp\\1.ts";
		
		try 
		{
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
            System.out.println("File downloaded ok");
		}
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		download("http://d2vvqvds83fsd.cloudfront.net/vin02/vsmedia/_definst_/smil:event/18/36/06/3/rt/1/resources/180919_PID_Intelligent_Enterprise_Gruenewald_720p-5F92.smil/media_b433000_1.ts");

	}

}
