package taskone;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///task";
        String user = "root";
        String password = "fast123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false); // start transaction

            try (PreparedStatement updateSales = conn.prepareStatement("UPDATE PERSON SET AMOUNT = AMOUNT + 10 WHERE ID = ?");
                 PreparedStatement updateTotal = conn.prepareStatement("UPDATE PERSON SET TOTAL = TOTAL + 10 WHERE ID = ?")) {

                updateSales.setInt(1, 100);
                updateSales.executeUpdate();

                updateTotal.setInt(1, 100);
                updateTotal.executeUpdate();

                conn.commit(); // commit transaction
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                conn.rollback(); // rollback transaction
                System.out.println("Transaction rolled back. Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
