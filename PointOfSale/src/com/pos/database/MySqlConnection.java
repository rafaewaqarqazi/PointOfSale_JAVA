package com.pos.database;
import java.sql.*;
import javax.swing.JOptionPane;


public class MySqlConnection{
    private static Connection conn = null;

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "pointofsale";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root"; 
    private static final String password = "";

  public static Connection getConnection() {
        try {
          Class.forName(driver).newInstance();
          conn =  DriverManager.getConnection(url+dbName,userName,password);
        } 
        catch (SQLException e) 
        {
         JOptionPane.showMessageDialog(null,"Error While Connecting to Database!\n\nPlease Start Database Server and Restart Application!\n\nApplication is Going To Close!","Cannot Connect To Database!",JOptionPane.ERROR_MESSAGE);
         System.exit(0);
        }
        catch (ClassNotFoundException ex) {  
                JOptionPane.showMessageDialog(null, "Driver Not Found!");
        }
       catch (Exception e){
           System.out.println(e.getMessage());
       }

        return conn;
  }

}