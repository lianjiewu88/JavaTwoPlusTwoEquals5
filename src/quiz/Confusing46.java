package quiz;

public class Confusing46 {
    private Confusing46( Object o ) {
         System.out.println("Object");
    }
    private Confusing46(double[] array) {
        System.out.println("double array");
    }
     public static void main(String[] args){
       // new Confusing46(null); // double array
    	 new Confusing46((Object)null); // solution!
     }
}
