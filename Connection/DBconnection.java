package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBconnection {

    public static Connection getConnection() {
        Connection cons = null;
        try {
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/doan", "root", "");
            //System.out.println("Ket noi mysql thanh cong");
            // System.out.println(cons.getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null,ex);
        }
        return cons;
    }
    public static void main(String[] args) {
        System.err.println(getConnection());
    }
}
