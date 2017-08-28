package odata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import odata.model.Ticket;
import odata.model.TicketName;

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
import org.apache.olingo.odata2.api.batch.BatchException;
import org.apache.olingo.odata2.api.client.batch.BatchChangeSet;
import org.apache.olingo.odata2.api.client.batch.BatchChangeSetPart;
import org.apache.olingo.odata2.api.client.batch.BatchPart;
import org.apache.olingo.odata2.api.client.batch.BatchSingleResponse;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketCreationRunner implements Runnable {

	private final String SERVICEURL = "https://qxl-cust233.dev.sapbydesign.com/sap/c4c/odata/v1/c4codata/ServiceRequestCollection";
	private HttpClient m_httpClient;
	private RequestConfig mConfig;
	private String boundary = "batch_" + UUID.randomUUID().toString();

	private String getXSRFToken() {
		final HttpGet get = new HttpGet(SERVICEURL);
		get.setHeader("Authorization", "Basic V0FOR0pFUlJZNjI4MTg6U2FwdGVzdDE=");
		get.setHeader("X-CSRF-Token", "Fetch");
		get.setConfig(mConfig);

		HttpResponse response;
		try {
			response = getHttpClient().execute(get);
			String xSRFToken = response.getFirstHeader("X-CSRF-Token")
					.getValue();
			System.out.println("XSRF Token got: " + xSRFToken);
			return xSRFToken;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TicketCreationRunner() {
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
		final HttpPost post = new HttpPost(
				URI.create("https://qxl-cust233.dev.sapbydesign.com/sap/c4c/odata/v1/c4codata/$batch"));
		post.setHeader("Content-Type", "multipart/mixed;boundary=" + boundary);
		post.setHeader("Authorization",
				"Basic V0FOR0pFUlJZNjI4MTg6U2FwdGVzdDE=");
		post.setHeader("X-CSRF-Token", getXSRFToken());
		HttpEntity entity = new StringEntity(body);

		post.setEntity(entity);
		post.setConfig(mConfig);

		HttpResponse response = getHttpClient().execute(post);

		System.out.println("Response statusCode for Batch => "
				+ response.getStatusLine().getStatusCode());

		return response;
	}

	public String serializeTicketDeepInsert(Ticket t) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = mapper.writeValueAsString(t);
		System.out.println("JSON: " + jsonInString);
		return jsonInString;
	}

	@SuppressWarnings("deprecation")
	private void createTicket(Ticket ticket) {
		List<BatchPart> batchParts = new ArrayList<BatchPart>();

		BatchChangeSet changeSet = BatchChangeSet.newBuilder().build();
		String contentId = UUID.randomUUID().toString();

		Map<String, String> changeSetHeaders = new HashMap<String, String>();
		changeSetHeaders.put("Content-Type", "application/json");
		changeSetHeaders.put("Content-ID", contentId);
		changeSetHeaders.put("Accept", "application/json");

		String uriTicket = new StringBuilder("ServiceRequestCollection")
				.toString();

		BatchChangeSetPart changeRequestTicket = null;
		try {
			String bodyString = serializeTicketDeepInsert(ticket);
			System.out.println("Body String: " + bodyString);
			changeRequestTicket = BatchChangeSetPart.method("POST")
					.uri(uriTicket).body(bodyString).headers(changeSetHeaders)
					.contentId(contentId).build();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		changeSet.add(changeRequestTicket);

		batchParts.add(changeSet);

		InputStream body = EntityProvider.writeBatchRequest(batchParts,
				boundary);

		String payload = null;
		try {
			payload = IOUtils.toString(body);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String serviceUrl = SERVICEURL;

		HttpResponse batchResponse = null;
		try {
			batchResponse = executeBatchCall(serviceUrl, payload);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("$batch response getStatusCode => "
				+ batchResponse.getStatusLine().getStatusCode());

		for (Header h : batchResponse.getAllHeaders()) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

		InputStream responseBody = null;
		try {
			responseBody = batchResponse.getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ThreadExecutionRecord.recordEndTimestamp(Thread.currentThread().getName());
		String contentType = batchResponse.getFirstHeader(
				HttpHeaders.CONTENT_TYPE).getValue();

		String response = null;
		try {
			response = IOUtils.toString(responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Process the batch response to get the ticket key
		List<BatchSingleResponse> responses = null;
		try {
			responses = EntityProvider.parseBatchResponse(
					IOUtils.toInputStream(response), contentType);
		} catch (BatchException e) {
			e.printStackTrace();
		}
		for (BatchSingleResponse rsp : responses) {
			// Look for only created entries
			System.out.println("Single Response status code => "
					+ rsp.getStatusCode());
			System.out.println("Single response: " + rsp.getBody());
			if (Integer.parseInt(rsp.getStatusCode()) == 201) {

				String locationUrl = rsp.getHeader("location");
				if (!StringUtils.isBlank(locationUrl)) {
					String ticketUUID = StringUtils.substringBetween(
							locationUrl, "'");
					System.out.println(ticketUUID);
				}
			}
		}
	}

	@Override
	public void run() {
		String postfix = UUID.randomUUID().toString();
		TicketName name = new TicketName("Jerry OData Ticket:" + postfix, "EN");
		
		Ticket ticket = new Ticket(name, "3");

		this.createTicket(ticket);
	}
}
