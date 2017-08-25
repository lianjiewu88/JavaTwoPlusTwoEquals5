package odata;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import odata.model.Ticket;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.olingo.odata2.api.client.batch.BatchChangeSet;
import org.apache.olingo.odata2.api.client.batch.BatchChangeSetPart;
import org.apache.olingo.odata2.api.client.batch.BatchPart;
import org.apache.olingo.odata2.api.client.batch.BatchSingleResponse;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class TicketCreationRunner implements Runnable{

	private final String SERVICEURL = "https://qxl-cust233.dev.sapbydesign.com/sap/c4c/odata/v1/c4codata/AccountCollection";
	private HttpClient m_httpClient;
	private RequestConfig mConfig;
	private String boundary = "batch_" + UUID.randomUUID().toString();

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
	
	private HttpResponse executeBatchCall(String serviceUrl, final String body)
			throws ClientProtocolException, IOException {
		final HttpPost post = new HttpPost(URI.create(serviceUrl + "/$batch"));
		post.setHeader("Content-Type", "multipart/mixed;boundary=" + boundary);
		post.setHeader("Authorization", "Basic V0FOR0pFUlJZNjI4MTg6U2FwdGVzdDE=");
		post.setHeader("X-CSRF-Token", getXSRFToken());
		HttpEntity entity = new StringEntity(body);

		post.setEntity(entity);

		for (Header h : post.getAllHeaders()) {
			logger.info(h.getName() + " : " + h.getValue());
		}

		HttpResponse response = getHttpClient().execute(post);

		logger.info("Response statusCode => "
				+ response.getStatusLine().getStatusCode());

		return response;
	}
	
	@SuppressWarnings("deprecation")
	private void createTicket(Ticket ticket){
		List<BatchPart> batchParts = new ArrayList<BatchPart>();

		BatchChangeSet changeSet = BatchChangeSet.newBuilder().build();
		String contentId = UUID.randomUUID().toString();

		Map<String, String> changeSetHeaders = new HashMap<String, String>();
		changeSetHeaders.put("Content-Type", "application/json");
		changeSetHeaders.put("Content-ID", contentId);
		changeSetHeaders.put("Accept", "application/json");

		String uriTicket = new StringBuilder("ServiceRequestCollection").toString();

		BatchChangeSetPart changeRequestTicket = BatchChangeSetPart
				.method("POST").uri(uriTicket)
				.body(serializeTicketDeepInsert(ticket))
				.headers(changeSetHeaders).contentId(contentId).build();

		changeSet.add(changeRequestTicket);

		batchParts.add(changeSet);

		InputStream body = EntityProvider.writeBatchRequest(batchParts,
				boundary);

		String payload = IOUtils.toString(body);

		System.out.println("$batch request : ");

		String serviceUrl = SERVICEURL;

		HttpResponse batchResponse = executeBatchCall(serviceUrl, payload);

		System.out.println("$batch response getStatusCode => "
				+ batchResponse.getStatusLine().getStatusCode());

		for (Header h : batchResponse.getAllHeaders()) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

		InputStream responseBody = batchResponse.getEntity().getContent();
		String contentType = batchResponse.getFirstHeader(
				HttpHeaders.CONTENT_TYPE).getValue();
		
		String response = IOUtils.toString(responseBody);

		// Process the batch response to get the ticket key
		List<BatchSingleResponse> responses = EntityProvider
				.parseBatchResponse(IOUtils.toInputStream(response),
						contentType);
		for (BatchSingleResponse rsp : responses) {
			// Look for only created entries
			System.out.println("Single Response status code => " + rsp.getStatusCode());
			if (Integer.parseInt(rsp.getStatusCode()) == 201 ) { 

				String locationUrl = rsp.getHeader("location");
				if (!StringUtils.isBlank(locationUrl)) {
					String ticketUUID = StringUtils.substringBetween(locationUrl,
							"'");
					System.out.println(ticketUUID);
				}
			}
		}
	}
	
	
    @Override
	public void run(){
		System.out.println("Token: " + this.getXSRFToken());
	}    	
}

