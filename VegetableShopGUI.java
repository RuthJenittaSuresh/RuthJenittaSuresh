import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VegetableShopGUI extends JFrame {
    private JTextField itemNameField, quantityField;
    private JTextArea itemListArea;
    private JButton addButton, viewButton;
    private Connection connection;
    private PreparedStatement insertStatement, selectStatement;

    public VegetableShopGUI() {
        // Set up the JFrame
        super("Vegetable Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create and set up the GUI components
        itemNameField = new JTextField(20);
        quantityField = new JTextField(5);
        itemListArea = new JTextArea(10, 30);
        addButton = new JButton("Add to Cart");
        viewButton = new JButton("View Cart");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(addButton);
        panel.add(viewButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(itemListArea), BorderLayout.CENTER);

        // Connect to the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/vegetable_shop_db", "root", "password");

            insertStatement = connection.prepareStatement("INSERT INTO items (name, quantity) VALUES (?, ?)");
            selectStatement = connection.prepareStatement("SELECT name, quantity FROM items");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addToCart() {
        String itemName = itemNameField.getText();
        String quantity = quantityField.getText();

        try {
            insertStatement.setString(1, itemName);
            insertStatement.setString(2, quantity);
            insertStatement.executeUpdate();

            itemNameField.setText("");
            quantityField.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void viewCart() {
        itemListArea.setText("");

        try {
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                String quantity = resultSet.getString("quantity");
                itemListArea.append(itemName + " - " + quantity + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		public static void main(String[] args)
    }
}