package DAO;

import javax.swing.*;
import java.sql.*;

public class ConnectionDAO {
    public Connection getConnection() throws Exception {
        try{
            String url ="jdbc:mysql://localhost:3306/register_system";
            String username = "admin";
            String password = "123";

            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        }catch(Exception e){
            System.out.println(e);
        }

        JOptionPane.showMessageDialog(null,"Connected with Database!");
        return null;
    }
}
