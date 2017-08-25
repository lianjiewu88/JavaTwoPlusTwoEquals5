package odata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import sun.misc.IOUtils;

public class CustomerQueryTest {

	HttpClient m_httpClient;

	private HttpClient getHttpClient() {
		if (this.m_httpClient == null) {
			this.m_httpClient = HttpClientBuilder.create().build();
		}

		return this.m_httpClient;
	}

	private void test() throws ClientProtocolException, IOException {
		String serviceUrl = "https://qxl-cust233.dev.sapbydesign.com/sap/c4c/odata/v1/c4codata/AccountCollection/?$search='Wang'";
		// serviceUrl = "http://www.baidu.com";
		final HttpGet get = new HttpGet(serviceUrl);
		get.setHeader("Authorization", "Basic V0FOR0pFUlJZNjI4MTg6U2FwdGVzdDE=");

		HttpHost proxy = new HttpHost("proxy.wdf.sap.corp", 8080, "http");

		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		get.setConfig(config);

		HttpResponse response = getHttpClient().execute(get);

		InputStream stream = response.getEntity().getContent();
		String result = new BufferedReader(new InputStreamReader(stream)).lines()
				   .parallel().collect(Collectors.joining("\n"));
		
		System.out.println("response: " + result );

	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		new CustomerQueryTest().test();
	}

}
