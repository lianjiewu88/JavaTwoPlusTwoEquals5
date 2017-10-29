package sap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class TEST
{
	public static void main(String[] args)
	{
		Utility.SetProxy();
		List<String> urlList = Utility.getURLList("c:\\temp\\url.txt");
		for(int i = 0; i < urlList.size(); i++)
			System.out.println("Index: " + i + "-" + URLFetcher.getStaticURL(urlList.get(i)));
	}
}