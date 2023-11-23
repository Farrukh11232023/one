package taskone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePagination {
    private static final String DB_URL = "jdbc:mysql:///task";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "fast123";
    private static int currentPage = 1;
    private static int recordsPerPage = 10;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Database Pagination");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel();
            JButton prevButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");
            panel.add(prevButton);
            panel.add(nextButton);
            frame.add(panel, BorderLayout.SOUTH);

            prevButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentPage > 1) {
                        currentPage--;
                        updateTextArea(textArea);
                    }
                }
            });

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentPage++;
                    updateTextArea(textArea);
                }
            });

            frame.setSize(400, 300);
            frame.setVisible(true);

            updateTextArea(textArea);
        });
    }

    private static void updateTextArea(JTextArea textArea) {
        textArea.setText("");
        try (Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person LIMIT ?, ?")) {
            stmt.setInt(1, (currentPage - 1) * recordsPerPage);
            stmt.setInt(2, recordsPerPage);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    textArea.append(rs.getString("name") + " is " + rs.getInt("age") + " years old\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
