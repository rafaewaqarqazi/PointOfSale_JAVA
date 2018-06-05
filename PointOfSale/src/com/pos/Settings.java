package com.pos;

import com.pos.database.QueryExecution;
import java.sql.ResultSet;

public class Settings {

    private QueryExecution dbQuery;
    public Settings(){
        dbQuery = new QueryExecution();
    }
    public boolean addUser(String username, String password, String userType){
            String query = "INSERT INTO users (username,password,userType) VALUES ('"+username+"','"+password+"','"+userType+"')";
            if(dbQuery.executeUpdateDeleteInsertQuery(query))
               return true;
            else 
               return false;
    }
    
    public boolean addCategory(String catName){
        String query = "INSERT INTO category (categoryName) VALUES ('"+catName+"')";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;  
    }
    
    public boolean addUnit(String unitName){
        String query = "INSERT INTO unit (unitName) VALUES ('"+unitName+"')";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }

    public ResultSet getUsersTableResult(){
        String query = "SELECT username,password,userType FROM users";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getCategoryTableResult(){

        String query = "SELECT * FROM category ORDER BY categoryID";
        return dbQuery.executeSelectQuery(query);
    }
     
    public ResultSet getUnitTableResult(){
        String query = "SELECT * FROM unit ORDER BY unitID";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getExistingUserID(String content){
        String query = "SELECT UserID FROM users WHERE username ='"+content+"'";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getResultFromUsersTable(String content){

        String query = "SELECT username,password,userType FROM users WHERE username ='"+content+"'"; 
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getResultFromCategoryTable(String catID){

        String query = "SELECT * FROM category WHERE categoryID ='"+catID+"'";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getResultFromUnitTable(String unitID){

        String query = "SELECT * FROM unit WHERE unitID ='"+unitID+"'";  
        return dbQuery.executeSelectQuery(query);
    }
    
    public boolean updateExistingUsers(String username,String pass,String type){
        String query = "UPDATE users SET password ='"+pass+"',userType = '"+type+"' WHERE username ='"+username+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    
    public boolean updateExistingCategory(String catID,String catName){
        String query = "UPDATE category SET categoryName ='"+catName+"' WHERE categoryID ='"+catID+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    
    public boolean updateExistingUnit(String unitID,String unitName){
        String query = "UPDATE unit SET unitName ='"+unitName+"' WHERE unitID ='"+unitID+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    public boolean deleteExistingUsers(String username){
        Login l = new Login();
        if (!(username.equals(l.getOnlineUser()))) {
            String query = "DELETE FROM users WHERE username ='"+username+"'";
            if(dbQuery.executeUpdateDeleteInsertQuery(query))
                return true;
            else 
                return false;
        }
        else{
            return false;
        }
    }
    
    public boolean deleteExistingCategory(String catName){
        String query = "DELETE FROM category WHERE categoryName ='"+catName+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    
    public boolean deleteExistingUnit(String unitName){
            String query = "DELETE FROM unit WHERE unitName ='"+unitName+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
}
