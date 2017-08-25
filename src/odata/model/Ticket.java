package odata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// JSON: {"ticketName":{"content":"Jerry","languageCode":"EN"},"priorityCode":"3"}


public class Ticket {
	// Jerry 2017-08-25 10:32PM the property name will determine the attribute
	// name in final json file
	// private TicketName ticketName;

	private TicketName Name;
	private String ServicePriorityCode;
	
	@JsonProperty("ServicePriorityCode")
	public String getPriorityCode(){
		return this.ServicePriorityCode;
	}
	
	@JsonProperty("Name")
	public TicketName getTicketName(){
		return this.Name;
	}
	
	@JsonProperty("Name")
	public void setTicketName(TicketName name){
		this.Name = name;
	}
	public Ticket(TicketName name, String priorityCode){
		this.Name = name;
		this.ServicePriorityCode = priorityCode;
	}
	
	@JsonProperty("ServicePriorityCode")
	public void setCode(String code){
		this.ServicePriorityCode = code;
	}
}