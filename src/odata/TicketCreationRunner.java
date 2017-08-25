package odata;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TicketCreationRunner implements Runnable{

	private final String SERVICEURL = "https://qxl-cust233.dev.sapbydesign.com/sap/c4c/odata/v1/c4codata/AccountCollection";
	private HttpClient m_httpClient;
	private RequestConfig mConfig;

	private String getXSRFToken(){
		final HttpGet get = new HttpGet(SERVICEURL);
		get.setHeader("Authorization", "Basic V0FOR0pFUlJZNjI4MTg6U2FwdGVzdDE=");
		get.setHeader("X-CSRF-Token", "Fetch");
		get.setConfig(mConfig);

		HttpResponse response;
		try {
			response = getHttpClient().execute(get);
			String xSRFToken = response.getFirstHeader("X-CSRF-Token").getValue();
			return xSRFToken;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TicketCreationRunner(){
		HttpHost proxy = new HttpHost("proxy.wdf.sap.corp", 8080, "http");
		mConfig = RequestConfig.custom().setProxy(proxy).build();
	}
	
	private HttpClient getHttpClient() {
		if (this.m_httpClient == null) {
			this.m_httpClient = HttpClientBuilder.create().build();
		}
		return this.m_httpClient;
	}
	
    @Override
	public void run(){
		System.out.println("Token: " + this.getXSRFToken());
	}    	
}

