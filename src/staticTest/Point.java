package staticTest;

import java.lang.reflect.Field;

public class Point {
	private int x;
	private int y;
	static private int count = 0;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		count++;
	}
	
	private static void accessStaticPrivate(Point point){
		Class classObject = point.getClass();
		try {
			Field countField = classObject.getDeclaredField("count");
			System.out.println("count: " + countField.get(point));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException e1 ) {
			e1.printStackTrace();
		} 
	}
	
	public static void main(String[] arg){
		
		System.setSecurityManager(new SecurityManager());
		Point a = new Point(1,2);
		accessStaticPrivate(a);
		
		Point b = new Point(1,3);
		accessStaticPrivate(b);
		
		Point c = new Point(1,4);
		accessStaticPrivate(c);
		
		Point d = new Point(1,5);
		accessStaticPrivate(d);
	}
}
