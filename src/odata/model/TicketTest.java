package odata.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketTest {
	static public void main(String[] arg) throws JsonProcessingException{
		TicketName name = new TicketName("Jerry", "EN");
		Ticket ticket = new Ticket(name, "3");
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = mapper.writeValueAsString(ticket);
		System.out.println("JSON: " + jsonInString);
	}
}
