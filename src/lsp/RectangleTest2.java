package lsp;
  
class Rectangle2 {
	protected int width;
	protected int height;
	protected int getArea(){
		return width * height;
	}
	
	protected void setWidth(int width){
		this.width = width;
	}
	
	protected void setHeight(int height){
		this.height = height;
	}
}

class Square2 extends Rectangle2{
	
	@Override 
	protected void setWidth(int width){
		this.width = this.height = width;
	}

	@Override 
	protected void setHeight(int height){
		this.height = this.width = height;
	}
}

class RectangleTest2{
	public static void main(String[] arg){
		
		Rectangle2 rectangle = new Rectangle2();
		rectangle.setHeight(4);
		rectangle.setWidth(3);
		System.out.println(rectangle.getArea());
		
		Square2 square = new Square2();
		square.setHeight(4);
		square.setWidth(3);
		System.out.println(square.getArea());
	}
}
