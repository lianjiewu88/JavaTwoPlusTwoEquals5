package connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.codec.binary.Base64;
import java.util.stream.Collectors;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class OPSystemConnectivity
{
	static public void main(String[] arg){
		final HttpGet get = new HttpGet("https://my306768.vlab.sapbydesign.com/sap/c4c/dis?ticketid=70714");
		HttpClient httpClient = HttpClientBuilder.create().build();

		String raw = "Crmops:Ondemand1";
        String encoded = Base64.encodeBase64String(raw.getBytes());
        get.setHeader("Authorization", "Basic " + encoded);
        
		HttpResponse response;
		try {
			long start = System.currentTimeMillis();
			response = httpClient.execute(get);
			InputStream stream = response.getEntity().getContent();
			String result = new BufferedReader(new InputStreamReader(stream)).lines()
					   .parallel().collect(Collectors.joining("\n"));
			System.out.println("response: " + result);
			stream.close();
			long end = System.currentTimeMillis() - start;
			System.out.println("time consumed: " + end );
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
	
}
