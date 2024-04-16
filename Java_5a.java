import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Java_5a extends JFrame {

     Connection con;
     Statement stmt;
     String connurl, sqlstr;
     ResultSet rset;

    Java_5a() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("AGE");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "1234");

            sqlstr = "select * from student";
            stmt = con.createStatement();
            rset = stmt.executeQuery(sqlstr);

            while (rset.next()) {
                int sid, sage;

                sid = rset.getInt(1);
                String sname = rset.getString("name");
                sage = rset.getInt(3);

                model.addRow(new Object[]{sid, sname, sage});
            }

            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String args[]) {
        new Java_5a();
    }
}
