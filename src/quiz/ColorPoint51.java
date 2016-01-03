package quiz;

class Point51 {
    protected final int x, y;
    // private final String name; // cached at construction time
    private String name;
    Point51( int x, int y){
       this.x = x;
       this.y = y;
       /* 在一个final类型的实例域被赋值之前，存在着取勇其值的可能性。此时它包含的仍旧是所属类型的缺

省值。只要一个构造器调用了一个已经被子类覆写了的方法，就会出问题，因为这种方式被调用的方法

总是在实例被初始化之前执行。不要在构造器中调用可覆写的方法。
*/		
       // name = makeName();
    }
  
    protected String makeName() {
       return "[" + x + "," + y + "]";
    }
    
    public final String toString() {
       // return name;
    	// 惰性初始化
    	if( name == null)
    		name = makeName();
    	return name;
    }
}

public class ColorPoint51 extends Point51 {
      private final String color; 
      ColorPoint51( int x, int y, String color){
    	 // 无法将 this.color = color提到super前面。
        super(x,y);
        this.color = color;
        
     }
     protected String makeName(){
        return super.makeName() + ":" + color;
     }
     
     public static void main(String[] args) {
    	 //[4,2]:null
    	 System.out.println(new ColorPoint51(4,2, "Purple"));
     }
     
}
      