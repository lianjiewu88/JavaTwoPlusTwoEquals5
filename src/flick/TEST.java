package flick;

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