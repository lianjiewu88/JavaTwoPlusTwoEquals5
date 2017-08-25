package odata.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Ticket {
	private TicketName ticketName;
	private String ServicePriorityCode;
	
	public Ticket(TicketName name, String priorityCode){
		this.ticketName = name;
		this.ServicePriorityCode = priorityCode;
	}
	
	@JsonProperty("ServicePriorityCode")
	public void setCode(String code){
		this.ServicePriorityCode = code;
	}
}