package corresponding;

import java.util.List;

public class CL_JAVA_CORRESPONDING {
	private List<?> source;
	private List<?> dest;
	private CL_MAPPING[] map;
	
	private CL_JAVA_CORRESPONDING(){
		
	}
	private CL_JAVA_CORRESPONDING(List<?> source, List<?> dest, CL_MAPPING[] mapping){
		this.source = source;
		this.dest = dest;
		this.map = mapping;
	}
	
	static public CL_JAVA_CORRESPONDING CREATE(List<?> source, List<?> dest, CL_MAPPING[] mapping){
		return new CL_JAVA_CORRESPONDING(source, dest, mapping);
	}
	
	public List<?> execute(){
		for( int i = 0; i < this.map.length; i++){
			
		}
		return null;
	}
}
