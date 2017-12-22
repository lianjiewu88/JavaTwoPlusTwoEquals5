package cloudsynchronizer;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Synchronizer {
	
		final private static String POST_PREFIX = "https://ldai2ag3.wdf.sap.corp:44356/sap/crm/bluecrystal_obd?sap-client=001&upload=";
		
		private static String getRequestURL(String str)
		{
			String url = POST_PREFIX + str;
	        url = url + "&sid=" + Math.random();
	        return url;
		}
	 
	 private static void sendHTTPPostRequest(String str) 
	 { 
		 HttpClient httpclient = new DefaultHttpClient();
		 String raw = "WANGJER:Saptest1"; 
		 String encoded = Base64.encodeBase64String(raw.getBytes());
	 
	     HttpPost httppost = new HttpPost(getRequestURL(str));
	     MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
	     builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	        
	     builder.addTextBody("version", "1.0.1");
	     builder.addTextBody("corporate", "sap");
	     try {
	            httppost.addHeader("Content-Type","multipart/form-data; boundary=assdsfdffafasf");
	            httppost.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
	            httppost.addHeader("Authorization", "Basic " + encoded);  
	            
	            HttpResponse httpresponse = httpclient.execute(httppost);
	            HttpEntity resEntity = httpresponse.getEntity();
	            String response = EntityUtils.toString(resEntity);
	            System.out.println(response);
	        }
	        catch (IOException e)
	        {
	         System.out.println(e.getLocalizedMessage());
	         e.printStackTrace();
	        }
	 }
	 
	 public static void main(String[] args) throws ClientProtocolException, IOException  
	 {  
		 sendHTTPPostRequest("&cmd=003&partner_id=MC7834");
	 }  
}