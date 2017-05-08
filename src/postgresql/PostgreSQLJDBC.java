package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
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
		jdbcTest.select();
	}
}