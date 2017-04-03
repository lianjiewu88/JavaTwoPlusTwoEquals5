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
	
	public static void print(){
		System.out.println("Total count: " + count);
	}
	
	public static void main(String[] arg){
		Point a = new Point(1,2);
		Class class1 = a.getClass();
		Field[] field = class1.getDeclaredFields();
		for( int i = 0 ; i < field.length; i++){
			Field each = field[i];
			try {
				System.out.println(each.get(a));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Point b = new Point(1,3);
		Point c = new Point(1,4);
		Point d = new Point(1,5);
		Point.print();
	}
	
}
