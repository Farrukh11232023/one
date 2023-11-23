package taskone;




import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUIAPP extends Application {

	
	private static final String USERNAME = "root";
    private static final String PASSWORD = "fast123";
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            String text = textField.getText();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql:///task",USERNAME,PASSWORD);
            		
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person WHERE name = ?")) {
                stmt.setString(1, text);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getString("name") + " is " + rs.getInt("age") + " years old");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(textField, button);
        Scene scene = new Scene(vbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
