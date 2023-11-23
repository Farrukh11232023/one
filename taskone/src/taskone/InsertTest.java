package taskone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class InsertTest {
// values specific to each database
private static final String URL = "jdbc:mysql:///task";
private static final String USERNAME = "root";
private static final String PASSWORD = "fast123";

public static void main(String[] args) {
// declare variables
 Scanner scan = null;
 int sno = 0;
 String sname = null, sadd = null;
 float avg = 0.0f;
 String query = null;
 Connection con = null;
 Statement st = null;
 int count = 0;

 try {
 // create Scanner class object
 scan = new Scanner(System.in);
 // read input
 if(scan != null) {
 System.out.print("Enter student number: ");
 sno = scan.nextInt();
 System.out.print("Enter student name: ");
 sname = scan.next();
 System.out.print("Enter student address: ");
 sadd = scan.next();
 }
 // prepare SQL query
 query = "INSERT INTO PERSON VALUES ("
 + sno + ", " + "\'" + sname +"\' ,"
 +"\'"+ sadd + "\') ";
 // establish the connection
 con = DriverManager.getConnection(
 URL, USERNAME, PASSWORD);
 // create JDBC statement object
 if(con != null) {
 st = con.createStatement();
 }
 // execute the SQL query
 if(st != null) {
 count = st.executeUpdate(query);
 }
 // display result
 System.out.println(count + " record inserted.");
 } catch(SQLException se) {
 se.printStackTrace();
 } catch(Exception e) {
 e.printStackTrace();
 } // try-catch block end
finally {
 // close JDBC objects
 try {
 if(st != null) st.close();
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
 }//finally block end
}
}