package corresponding;

public class CL_MAPPING {
	private String source;
	private String target;
	public CL_MAPPING(String src, String dest){
		this.source = src;
		this.target = dest;
	}
	
	public String getSrc(){
		return this.source;
	}
	public String getTarget(){
		return this.target;
	}
}
