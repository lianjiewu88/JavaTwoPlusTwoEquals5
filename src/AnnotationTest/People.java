package AnnotationTest;

@Description(author = "Jerry", desc = "Class annotation", age = 35)
public class People {
	
	@Description(author = "Jerry 2", desc = "method annotation", age = 35)
	public void hello(){
		
	}
	
	public static void main(String[] arg){
	        try {
	            // 使用类加载器加载类
	            Class c = Class.forName("AnnotationTest.People");
	            // 找到类上面的注解
	            boolean isExist = c.isAnnotationPresent(Description.class);
	            // 上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
	            if (isExist) {
	                // 拿到注解实例，解析类上面的注解
	                
	            	Description d = (Description) c.getAnnotation(Description.class);
	                System.out.println(d.desc());
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}
