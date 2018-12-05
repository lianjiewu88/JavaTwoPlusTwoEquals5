package youdao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class YoudaoNote {

	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
	     
	    String urlStr = "http://note.youdao.com/yws/public/note/7ef850508e67ea9cf586094766959f32?keyfrom=public";
	        HttpGet get = new HttpGet(urlStr);
	        try {
	             
	            HttpResponse response = client.execute(get);
	            HttpEntity entity = response.getEntity();
	            String result = EntityUtils.toString(entity, "UTF-8");
	            System.out.print(result);
	             
	        } catch (Exception e){
	        	e.printStackTrace();
	        }

	}

}
