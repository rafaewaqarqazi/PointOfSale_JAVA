package com.pos.database;


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecution {
    private Connection conn;
    public Statement stmt;
    public ResultSet result = null;
    public QueryExecution(){
        conn=MySqlConnection.getConnection();
    }
    public boolean executeUpdateDeleteInsertQuery(String query){
      try {
          stmt = conn.createStatement();
          stmt.executeUpdate(query);
          return true;
      } catch (MySQLIntegrityConstraintViolationException ex) {
          return false;
      } catch (SQLException ex) {
            return false;
      }
  }
  public ResultSet executeSelectQuery(String query){
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
  }
}
