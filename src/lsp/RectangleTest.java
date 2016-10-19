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
	public int getCircle(){
		// return some value
		return 0; 
	}
}

class RectangleTest{
	public static void main(String[] arg){
		Square square = new Square();
		square.setSize(5, 3);
		System.out.println(square.getArea());
	}
	
}
