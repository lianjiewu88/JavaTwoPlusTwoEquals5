package flick;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLFetcher 
{
	static public String getStaticURL(String ID)
	{
		String strFullURL = "http://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key=b29c2c7f2a7cf6deccdd2672a7893025&photo_id="
				+ ID + "&format=rest";
		URL url;
		StringBuffer sb = new StringBuffer();
		try 
		{
			url = new URL(strFullURL);
			URLConnection urlConnection;
			urlConnection = url.openConnection(); 
			urlConnection.setRequestProperty("Accept-Charset", "UTF-8"); 
			InputStream is = urlConnection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int numCharsRead;
			char[] charArray = new char[1024];
			while ((numCharsRead = in.read(charArray)) > 0) 
			{
				sb.append(charArray, 0, numCharsRead);
			}
			in.close();
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Utility.getStaticURL(sb.toString());
	}
}
