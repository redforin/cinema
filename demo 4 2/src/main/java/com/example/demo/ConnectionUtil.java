package com.example.demo;

import java.sql.*;
import javax.swing.*;
public class ConnectionUtil {
    Connection conn = null;
    public static Connection connectdb()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","12345678");
            return conn;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
