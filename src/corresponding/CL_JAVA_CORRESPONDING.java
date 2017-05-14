package corresponding;

import java.lang.reflect.Field;
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
		if( source.size() != dest.size()){
			return null;
		}
		if( source.isEmpty()){
			return null;
		}
		return new CL_JAVA_CORRESPONDING(source, dest, mapping);
	}
	
	public List<?> execute(){
		
		for( int i = 0; i < this.map.length; i++){
			map(source, dest, map[i]);
		}
		return dest;
	}
	
	private void map(List<?> source, List<?> dest, CL_MAPPING map){
		for( int i = 0; i < source.size(); i++)
			mapEach(source.get(i), dest.get(i), map.getSrc(), map.getTarget());
	}
	
	private void mapEach(Object source, Object target, String sourceField, String targetField){   

        Class<?> sourceClass = source.getClass();  
        Class<?> targetClass = target.getClass();
        
		try {
			Field srcField = sourceClass.getDeclaredField(sourceField);
			srcField.setAccessible(true);
	        Object srcValue = srcField.get(source);
	        
	        Field destField = targetClass.getDeclaredField(targetField);
	        destField.setAccessible(true);
	        destField.set(target, srcValue);
		} catch (NoSuchFieldException | IllegalArgumentException | SecurityException
				| IllegalAccessException e) {
			e.printStackTrace();
		} 
	}
}
