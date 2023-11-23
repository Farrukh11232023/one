package taskone;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class one {
	
	    public static void main(String[] args) {
	    	
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	// variables for connection
	    	final String url = "jdbc:mysql:///test";
	    	final String user = "root";
	    	final String password = "yourpassword";
	    	
	    	
	    	
	    	 //establish the connection
	    	 Connection con = DriverManager.getConnection(url, user, password);

	    	 // display status message
	    	  if (con == null) {
	    	  System.out.println("JDBC connection is not established");return;
	    	  } else {
	    	 System.out.println("Congratulations, JDBC connection is established successfully.\n");

	    	  }

	    	 //Remember to close the JDBC connection
	    	  con.close();
	    	 }
	    	 
	    	
	    	
	    	
	    	
	    	
}
	    	
	    	
	    	
	    	
	    	
	        // JDBC URL for MySQL database
	        //String jdbcUrl = "jdbc:mysql://localhost:3306/my_database_name?useSSL=false&serverTimezone=UTC";
	        //String username = "your_username";
	        //String password = "your_password";

	        //try {
	            // Establishing the connection
	            //Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
	            
	            //if (connection != null) {
	                //System.out.println("Connected to the database!");
	                // Do your database operations here
	                // Example: Retrieve data, update data, etc.
	                
	                // Close the connection when done
	                //connection.close();
	            //}
	        //} catch (SQLException e) {
	            //System.out.println("Connection failed. Error: " + e.getMessage());
	            // Handle any SQL exception that occurred during connection
	            //e.printStackTrace();
	        //}
	    //}
	



