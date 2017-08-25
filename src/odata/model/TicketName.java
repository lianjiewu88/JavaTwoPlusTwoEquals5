package odata.model;

public class TicketName {
	private String content;
	private String languageCode;
	public TicketName(String name, String code){
		this.content = name;
		this.languageCode = code;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public String getLanguageCode(){
		return this.languageCode;
	}
	public void setContent(String name){
		this.content = name;
	}
	
	public void setlanguageCode(String code){
		this.languageCode = code;
	}
}
