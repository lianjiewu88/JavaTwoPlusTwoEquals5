package nestedClass;

public class LocalScreen {
    public void method() {
        new Runnable() {
              public void run() {
                   System.out.println(this.toString());
                   System.out.println(LocalScreen.this.toString()); 

                   onMake(LocalScreen.this);
              } 
              
              public String toString() { 
            	  return "An anonymous Runnable!"; 
              } 
        }.run(); 
    } 
    
    public String toString() { 
    	return "A LocalScreen object"; 
    } 
    
    public void onMake(LocalScreen ls) { 
    	System.out.println("On make called: " + ls);
    } 
    
    public static void main(String[] args) { 
    	new LocalScreen().method(); 
    } 
}
