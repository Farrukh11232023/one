package taskone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class delette {
    private static final String DELETE_PERSON_QUERY = "DELETE FROM PERSON WHERE ID = ?";
    private static final String URL = "jdbc:mysql:///task";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "fast123";

    public static void main(String[] args) {
        Scanner scan = null;
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            // Read input
            scan = new Scanner(System.in);
            if (scan != null) {
                System.out.println("Enter the person ID to delete: ");
                id = scan.nextInt();
            }

            // Establish the connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Prepare SQL query and store it in PreparedStatement object
            if (con != null)
                ps = con.prepareStatement(DELETE_PERSON_QUERY);

            // Set input values and execute query
            if (ps != null) {
                // Set input values to query parameters
                ps.setInt(1, id);
                // Execute the query
                result = ps.executeUpdate();
            }

            // Process the result
            if (result == 0)
                System.out.println("Record not deleted");
            else
                System.out.println("Record deleted successfully");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            try {
                if (ps != null) ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (scan != null) scan.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
