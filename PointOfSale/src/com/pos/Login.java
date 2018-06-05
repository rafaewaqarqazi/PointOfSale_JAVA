package com.pos;

import java.sql.*;
import com.pos.database.QueryExecution;

public class Login {  
    private String dbusername,dbpassword; 
    private static String userType,onlineUser;
    private QueryExecution dbQuery;
    ResultSet result;
    public Login(){
        dbusername = "";
        dbpassword = "";
        dbQuery = new QueryExecution();
    }
    //setters and getters
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getOnlineUser() {
        return onlineUser;
    }
    public void setOnlineUser(String onlineUser) {
        this.onlineUser = onlineUser;
    }
    //Other Methods
    
    public boolean evaluateAdmin(String username, String password){
      
        String query = "SELECT username,password FROM users WHERE (username = '"+username+"' AND userType = 'Admin' )";
        try {
            result = dbQuery.executeSelectQuery(query);
            while(result.next())
            {
                dbusername=result.getString(1);
                dbpassword=result.getString(2);
            }
            if (username.equals(dbusername) && password.equals(dbpassword) && !(dbusername.isEmpty() && dbpassword.isEmpty())){
                return true;
            }
            else
                return false;
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
        
    
        }
    public boolean evaluateEmployee(String username, String password){
       
        String query = "SELECT username,password FROM users WHERE (username = '"+username+"' AND userType = 'Employee' )";
        try {
            
            result = dbQuery.executeSelectQuery(query);
           
            while(result.next())
            {
                dbusername=result.getString(1);
                dbpassword=result.getString(2);
            }
            if (username.equals(dbusername) && password.equals(dbpassword) && !(dbusername.isEmpty() && dbpassword.isEmpty())) {
                return true;
            }
            else{
                return false;
            }
          
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

   return false;
    }
    
}
