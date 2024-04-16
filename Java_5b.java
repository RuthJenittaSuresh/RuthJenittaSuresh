import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Java_5b extends JFrame {

     Connection con;
     Statement stmt;
     String connurl, sqlstr;
     ResultSet rset;

     JComboBox<String> columnComboBox;

    Java_5b() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel selectLabel = new JLabel("Select :");
        columnComboBox = new JComboBox<>(new String[]{"ID", "NAME", "AGE", "TOTAL TABLE"});
        JButton showButton = new JButton("Show Data");
        topPanel.add(selectLabel);
        topPanel.add(columnComboBox);
        topPanel.add(showButton);
        add(topPanel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColumn = (String) columnComboBox.getSelectedItem();
                fetchData(selectedColumn, model);
            }
        });

        setVisible(true);
    }

    public void fetchData(String selectedColumn, DefaultTableModel model) {
        model.setRowCount(0);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "1234");

            if (selectedColumn.equals("TOTAL TABLE")) {
                sqlstr = "select id, name, age from student";
            } else {
                sqlstr = "select " + selectedColumn + " from student";
            }

            stmt = con.createStatement();
            rset = stmt.executeQuery(sqlstr);

            while (rset.next()) {
                if (selectedColumn.equals("ID")) {
                    int id = rset.getInt("ID");
                    model.addRow(new Object[]{id, "", ""});
                } else if (selectedColumn.equals("NAME")) {
                    String name = rset.getString("NAME");
                    model.addRow(new Object[]{"", name, ""});
                } else if (selectedColumn.equals("AGE")) {
                    int age = rset.getInt("AGE");
                    model.addRow(new Object[]{"", "", age});
                } else {
                    int id = rset.getInt("id");
                    String name = rset.getString("name");
                    int age = rset.getInt("age");
                    model.addRow(new Object[]{id, name, age});
                }
            }

            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Java_5b();
    }
}
