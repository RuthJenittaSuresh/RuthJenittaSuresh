import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class UniversityManagementSystem extends JFrame {
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField idField, nameField, departmentField;
    private JButton addButton, deleteButton;

    public UniversityManagementSystem() {
        setTitle("University Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating table model and table
        tableModel = new DefaultTableModel();
        studentTable = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Department");

        // Creating text fields
        idField = new JTextField(10);
        nameField = new JTextField(20);
        departmentField = new JTextField(20);

        // Creating buttons
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        // Adding action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        // Creating panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(departmentField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Adding components to the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        String department = departmentField.getText();

        if (tableModel.getRowCount() >= 25) {
            JOptionPane.showMessageDialog(this, "Maximum limit of 25 records reached.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (id.isEmpty() || name.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add student to the table
        tableModel.addRow(new Object[]{id, name, department});

        // Clear input fields
        idField.setText("");
        nameField.setText("");
        departmentField.setText("");
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Remove the selected row
        tableModel.removeRow(selectedRow);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UniversityManagementSystem frame = new UniversityManagementSystem();
                frame.setVisible(true);
            }
        });
    }
}