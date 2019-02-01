package odata;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class SimpleOrderCreator {

	private final String SERVICEURL = "https://my5000jerry.c4c.saphybriscloud.cn/sap/c4c/odata/cust/v1/zjerrysalesorder/CustomerQuoteCollection";
	HttpClient m_httpClient;

	private HttpClient getHttpClient() {
		if (this.m_httpClient == null) {
			this.m_httpClient = HttpClientBuilder.create().build();
		}
		return this.m_httpClient;
	}
	
	private String getCSRFToken(){
		final HttpGet get = new HttpGet(SERVICEURL);
		get.setHeader("Authorization", "Basic 0FOR0pFUlJZMTpTYXB0ZXN0MQ==");
		get.setHeader("x-csrf-token", "fetch");
		HttpHost proxy = new HttpHost("proxy.sha.sap.corp", 8080, "http");

		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		get.setConfig(config);

		HttpResponse response;
		String token = null;
		try {
			response = getHttpClient().execute(get);
			/*Header[] header = response.getAllHeaders();
			for( int i =0; i < header.length; i++){
				System.out.println("Header: " + header[i].getValue());
			}*/
			token = response.getFirstHeader("x-csrf-token").getValue();
			System.out.println("token: " + token);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	private void createSO(String token,String body) {
		final HttpPost post = new HttpPost(
				URI.create(SERVICEURL));
		post.setHeader("Authorization",
				"Basic 0FOR0pFUlJZMTpTYXB0ZXN0MQ==");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("X-CSRF-Token", token);
		HttpEntity entity = null;
		try {
			entity = new StringEntity(body);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.setEntity(entity);

		HttpResponse response = null;
		try {
			response = getHttpClient().execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Response statusCode for Batch => "
				+ response.getStatusLine().getStatusCode());
	}
	
	public static void main(String[] args) {
		SimpleOrderCreator tool = new SimpleOrderCreator();
		String token = tool.getCSRFToken();
		String body = "{" + 
    "\"Name\": \"Jerry Test 2019-1-23 16:05PM\"," + 
    "\"TypeCode\": \"2059\"," + 
    "\"BuyerID\":\"ee\"," + 
    "\"PartyID\":\"60102\", " + 
    "\"CustomerQuoteText\":[{" + 
    	"\"Text\": \"test comment by Jerry Wang\", " + 
    	"\"TypeCode\": \"10024\"" + 
    "}]," + 
    "\"CustomerQuoteItem\":[{ " + 
    	"\"ProductID\": \"1042416\"" + 
    "}]," + 
    "\"CustomerQuoteItemProposal\":[{" + 
    	"\"ProductUUID\": \"00163E72-09C6-1EE8-BBDC-AC5F0CB0D795\"," + 
    	"\"Quantity\": \"1\"," + 
    	"\"unitCode\": \"EA\"" + 
    "}]" + 
"}";
		
		tool.createSO(token, body);
	}
}
