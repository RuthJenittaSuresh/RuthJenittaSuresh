import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FoodOrganizingSystem extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/food_organizer";
    private static final String USER = "root";
    private static final String PASS = "W7301@jqir#";

    private Connection conn;
    private Statement stmt;

    private JTextField nameField, quantityField;
    private JButton addButton, viewButton, deleteButton;
    private JTextArea resultArea;

    public FoodOrganizingSystem() {
        initializeUI();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            createTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeUI() {
        setTitle("Food Organizer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        JLabel nameLabel = new JLabel("Food Name:");
        nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        addButton = new JButton("Add Food");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFood();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        viewButton = new JButton("View All Food");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFood();
            }
        });
        deleteButton = new JButton("Delete Food");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFood();
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS food (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), quantity INT)";
        stmt.executeUpdate(sql);
    }

    private void addFood() {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        try {
            String sql = "INSERT INTO food (name, quantity) VALUES ('" + name + "', " + quantity + ")";
            stmt.executeUpdate(sql);
            resultArea.append("Food added: " + name + ", Quantity: " + quantity + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewFood() {
        resultArea.setText("");
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM food");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                resultArea.append("ID: " + id + ", Name: " + name + ", Quantity: " + quantity + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteFood() {
        String name = nameField.getText();
        try {
            String sql = "DELETE FROM food WHERE name = '" + name + "'";
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                resultArea.append("Food deleted: " + name + "\n");
            } else {
                resultArea.append("Food not found: " + name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FoodOrganizingSystem();
            }
        });
    }
}