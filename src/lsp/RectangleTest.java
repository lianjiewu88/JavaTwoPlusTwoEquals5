package lsp;

class Rectangle {
	private int width;
	private int height;
	protected int getArea(){
		return width * height;
	}
	
	protected void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}
}

class Square extends Rectangle{
}

class RectangleTest{
	public static void main(String[] arg){
		Rectangle rectangle = new Rectangle();
		rectangle.setSize(5, 3);
		System.out.println(rectangle.getArea());
	}
	
}
