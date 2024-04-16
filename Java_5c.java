import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

 class Java_5c extends JFrame implements ActionListener {

    Connection con;
    Statement stmt;
    String connurl, sqlstr;
    ResultSet rset;

    JLabel nameLabel, srnoLabel, sageLabel;
    JTextField nameField, srnoField, sageField;
    JButton nextButton, prevButton;
    JPanel panel;

    Java_5c() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameLabel = new JLabel("Name:");
        srnoLabel = new JLabel("Id:");
        sageLabel = new JLabel("Age:");

        nameField = new JTextField(20);
        srnoField = new JTextField(10);
        sageField = new JTextField(5);

        panel = new JPanel();
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");

        nextButton.addActionListener(this);
        prevButton.addActionListener(this);

        panel.add(prevButton);
        panel.add(nextButton);

        setLayout(new GridLayout(4, 2));
        add(srnoLabel);
        add(srnoField);
        add(nameLabel);
        add(nameField);
        add(sageLabel);
        add(sageField);
        add(panel);

        setVisible(true);
    //}

    //public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "1234");
        } catch (ClassNotFoundException | SQLException ce) {
            ce.printStackTrace();
        }
    }

    public void retrieveRecords() {
        try {
            sqlstr = "select * from student";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sqlstr);

            if (rset.next()) {
                displayRecord();
            } else {
                JOptionPane.showMessageDialog(this, "No records found.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void displayRecord() {
        try {
            int srno, sage;

            srno = rset.getInt(1);
            String strname = rset.getString("name");
            sage = rset.getInt(3);

            srnoField.setText(String.valueOf(srno));
            nameField.setText(strname);
            sageField.setText(String.valueOf(sage));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == nextButton) {
                if (rset.next()) {
                    displayRecord();
                } else {
                    JOptionPane.showMessageDialog(this, "End of records.");
                }
            } else if (ae.getSource() == prevButton) {
                if (rset.previous()) {
                    displayRecord();
                } else {
                    JOptionPane.showMessageDialog(this, "Start of records.");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Java_5c obj = new Java_5c();
        obj.retrieveRecords();
    }
}
