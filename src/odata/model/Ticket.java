package odata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// JSON: {"ticketName":{"content":"Jerry","languageCode":"EN"},"priorityCode":"3"}


public class Ticket {
	private TicketName ticketName;
	private String ServicePriorityCode;
	
	public String getPriorityCode(){
		return this.ServicePriorityCode;
	}
	
	public TicketName getTicketName(){
		return this.ticketName;
	}
	public Ticket(TicketName name, String priorityCode){
		this.ticketName = name;
		this.ServicePriorityCode = priorityCode;
	}
	
	@JsonProperty("ServicePriorityCode")
	public void setCode(String code){
		this.ServicePriorityCode = code;
	}
}