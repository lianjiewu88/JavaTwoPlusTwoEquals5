package outputmanagement;

public abstract class AbstractController {
	public String postOutput(){
		String url = getHosturl();
		String result = runOutput(url);
		return handle(result);
	}
	
	private String getHosturl(){
		return "http://localhost:8080";
	}
	
	abstract protected String handle(String result);
	private String runOutput(String url){
		return url.toUpperCase();
	}
}
