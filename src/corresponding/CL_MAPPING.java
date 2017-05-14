package corresponding;

import java.util.function.Function;

public class CL_MAPPING {
	private String source;
	private String target;
	private Function<? extends Object, ? extends Object> function;
	public CL_MAPPING(String src, String dest, Function<? extends Object, ? extends Object> function){
		this.source = src;
		this.target = dest;
		this.function = function;
	}
	
	public String getSrc(){
		return this.source;
	}
	public String getTarget(){
		return this.target;
	}
	public Function<? extends Object, ? extends Object> getFunction(){
		return this.function;
	}
}
