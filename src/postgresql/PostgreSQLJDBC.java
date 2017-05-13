package postgresql;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PostgreSQLJDBC {

	private Connection connection = null;

	private void select() {
		try {
			int index = 0;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:9812/zproduct", "postgres", "Saptest1");
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM public.comm_product;";
			ResultSet rs = stmt.executeQuery(query);
	         while ( rs.next() ) {
	        	System.out.println("Row index: " + index++);
	            
	        	String  client = rs.getString("client");
	            System.out.println("Client: " + client);
	            
	            String  guid = rs.getString("product_guid");
	            System.out.println("Product guid: " + guid);
	            
	            Timestamp validFrom = rs.getTimestamp("valid_from");
	            System.out.println("Valid from: " + validFrom);
	         }
	         rs.close();
	         stmt.close();
	         connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void main(String args[]) {
		PostgreSQLJDBC jdbcTest = new PostgreSQLJDBC();
		//jdbcTest.select();
		jdbcTest.clobTest();
	}
	
	private void clobTest(){
		
		String description = null;
	    Clob myClob = null;
	    PreparedStatement pstmt = null;

	    try {
	    	connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:9812/zproduct", "postgres", "Saptest1");
	        String sql =
	            "select text " +
	            "from public.ztest2 " +
	            "where key1 = ?";

	        pstmt = this.connection.prepareStatement(sql);
	        pstmt.setString(1, "1");
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	System.out.println(rs.getString(1));
	            //myClob = rs.getClob(1);
	            //System.out.println("Length of retrieved Clob: " +
	            //    myClob.length());
	        }
	        // description = myClob.getSubString(1, 10);
	    } catch (SQLException sqlex) {
	        sqlex.printStackTrace();
	    } catch (Exception ex) {
	        System.out.println("Unexpected exception: " + ex.toString());
	    } finally {
	        if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    // System.out.println("Description: " + description);
	}
}