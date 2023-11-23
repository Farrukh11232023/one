package taskone;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class two {

	    public static void main(String[] args) {
	        // JDBC URL for MySQL database
	        String jdbcUrl = "jdbc:mysql://localhost:3306/my_database_name?useSSL=false&serverTimezone=UTC";
	        String username = "your_username";
	        String password = "your_password";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	            if (connection != null) {
	                System.out.println("Connected to the database!");

	                // Prompt user to enter student details
	                Scanner scanner = new Scanner(System.in);
	                System.out.print("Enter Student ID: ");
	                int studentId = scanner.nextInt();
	                scanner.nextLine(); // Consume newline
	                System.out.print("Enter Student Name: ");
	                String studentName = scanner.nextLine();
	                System.out.print("Enter Student Age: ");
	                int studentAge = scanner.nextInt();

	                // Prepare SQL statement for insertion
	                String insertQuery = "INSERT INTO students (student_id, name, age) VALUES (?, ?, ?)";
	                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

	                // Set parameters for the prepared statement
	                preparedStatement.setInt(1, studentId);
	                preparedStatement.setString(2, studentName);
	                preparedStatement.setInt(3, studentAge);

	                // Execute the insertion query
	                int rowsAffected = preparedStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    System.out.println("Student record inserted successfully!");
	                } else {
	                    System.out.println("Failed to insert student record.");
	                }

	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Connection failed. Error: " + e.getMessage());
	            // Handle any SQL exception that occurred during connection or insertion
	            e.printStackTrace();
	        }
	    }
	}

}
