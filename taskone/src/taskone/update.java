package taskone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class update {
// SQL query
private static final String UPDATE_PERSON_QUERY =
 "UPDATE PERSON SET NAME = ?, ADDRESS = ?"
 + "WHERE ID = ? ";
private static final String URL = "jdbc:mysql:///task";
private static final String USERNAME = "root";
private static final String PASSWORD = "fast123";
public static void main(String[] args) {
// declare variables
 Scanner scan = null;
 int id = 0;
 String name = null;
 String address = null;
 Connection con = null;
 PreparedStatement ps = null;
 int result = 0;

 try {
 //read input
 scan = new Scanner(System.in);
 if(scan != null) {
 System.out.println("Enter the existing person ID to update: ");
 id = scan.nextInt();
 System.out.println("Enter new details,");
 System.out.print("Name of the person: ");
 name = scan.next();
 System.out.print("Address: ");
 address = scan.next();
 }
 // establish the connection
 con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
 // compile SQL query and
 // store it in PreparedStatemet object
 if(con != null)
 ps = con.prepareStatement(UPDATE_PERSON_QUERY);

// set input values and execute query
 if(ps != null) {
 // set input values to query parameters
 ps.setString(1, name);
 ps.setString(2, address);
 ps.setInt(3, id);
 // execute the query
 result = ps.executeUpdate();
 }
 // process the result
 if(result == 0)
 System.out.println("Records not updated");
 else
 System.out.println("Records updated successfully");

 } catch(SQLException se) {
 se.printStackTrace();
 } catch(Exception e) {
 e.printStackTrace();
 } // end of try-catch block
 finally {
 // close JDBC objects
 try {
 if(ps != null) ps.close();
 } catch(SQLException se) {
 se.printStackTrace();
 }
 try {
 if(con != null) con.close();
 } catch(SQLException se) {
 se.printStackTrace();
 }
 try {
 if(scan != null) scan.close();
 } catch(Exception e) {
 e.printStackTrace();
 }
 } // end of finally block
}
}