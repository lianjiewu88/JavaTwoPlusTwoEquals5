package flick;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Utility
{
	static public void SetProxy()
	{
		System.setProperty("http.proxyHost", "proxy.wdf.sap.corp");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "proxy.wdf.sap.corp");
		System.setProperty("https.proxyPort", "8080");
	}
	
	static public List<String> getURLList(String file)
	{
		List<String> urlList = new ArrayList<String>();
		try 
		{
			FileInputStream f = new FileInputStream(file);
			BufferedReader dr = new BufferedReader(new InputStreamReader(f));   
			String url = null;
		    while ( ( url = dr.readLine() ) != null )
		    {
		    	urlList.add(getID(url));
		    }
		    f.close();
		    dr.close();
		} 
		catch (IOException e) 
		{
		}
		return urlList;
	}
	
	static private String getID(String url)
	{
		String[] a = url.split("/");
		return a[a.length-1];
	}
	
	static public String getStaticURL(String content)
	{
		String prefix = "source=";
		int index = content.indexOf("Original");
		if( index == -1)
			return null;
		String sub = content.substring(index);
		int source = sub.indexOf(prefix);
		if( source == -1)
			return null;
		int end = sub.indexOf("url=");
		if( end == -1)
			return null;
		return sub.substring(source + prefix.length(), end);
	}
}