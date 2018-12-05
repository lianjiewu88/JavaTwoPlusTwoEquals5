package youdao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class YoudaoNote {

	private static String title = null;
	
	private static List<String> getPicUrlList(String formattedUrl){
		HttpClient client = HttpClients.createDefault();
		List<String> resultPic = new ArrayList<String>();
		
	    HttpGet get = new HttpGet(formattedUrl);
	        try {
	            HttpResponse response = client.execute(get);
	            HttpEntity entity = response.getEntity();
	            String result = EntityUtils.toString(entity, "UTF-8");
	            JSONObject obj = new JSONObject(result);
	            
	            title = obj.get("tl").toString();
	            // System.out.println("title: " + title);
	            
	            String content = obj.get("content").toString();
	            System.out.println("content: " + content);
	            
	            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(content);
	            while (m.find()) {
	                    System.out.println(m.group(1));
	                    resultPic.add(m.group(1));
	            }
	        } catch (Exception e){
	        	e.printStackTrace();
	        }
	        return resultPic;
	}
	
	public static void main(String[] args) {
		String urlStr = "http://note.youdao.com/yws/public/note/7ef850508e67ea9cf586094766959f32?keyfrom=public";
		getPicUrlList(urlStr);
	}
}
