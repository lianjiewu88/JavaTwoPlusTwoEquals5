package reflection;

import java.lang.reflect.Method;

public class DuckType {

	public void print(boolean flag){
		System.out.println("Jerry: " + flag);
	}
	
	public  void reflectCall(Object obj){
		try {
			Class<?> clz = obj.getClass();
			Method m = clz.getMethod("print", boolean.class);
			m.invoke(obj, false);
		} catch ( Exception e){
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		DuckType tool = new DuckType();
		tool.reflectCall(tool);
	}

}
