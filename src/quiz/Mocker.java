package quiz;

import java.sql.SQLException;

public class Mocker<T extends Exception> {
     private void pleaseThrow(final Exception t) throws T {
    	 throw(T) t;
     }

     public String toString() {
    	 System.out.println("ok");
    	 return "OK";
     }
     public static void main(final String[] args) {
    	 
    	Mocker<RuntimeException> mocker = new Mocker<RuntimeException>();
    	System.out.println(mocker); // will call toString();
    	System.exit(-1);
        try {
           new Mocker<RuntimeException>().pleaseThrow(new SQLException());
        }
        //catch( final SQLException ex) {
        catch ( final RuntimeException ex) {
        	System.out.println("Runtime exception caught!");
                ex.printStackTrace();
             }
        }
}