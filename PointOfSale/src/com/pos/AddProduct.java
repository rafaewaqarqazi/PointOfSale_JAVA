package com.pos;
import java.sql.*;
import com.pos.database.QueryExecution;

public class AddProduct {
    private QueryExecution dbQuery;
    public AddProduct(){
        dbQuery = new QueryExecution();
    }
    public ResultSet getCategoryNameResult(){
        String query = "SELECT categoryName FROM category";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getUnitNameResult(){
        String query = "SELECT unitName FROM unit";
        return dbQuery.executeSelectQuery(query);
    }
    public boolean addProduct(String article,String category,String description,int quantity,String unit,int buyingPrice,int sellingPrice,String registrationDate){
        String query = "INSERT INTO products (article,category,description,quantity,unit,buyingPrice,sellingPrice,registrationDate,dateUpdated) VALUES ('"+article+"','"+category+"','"+description+"','"+quantity+"','"+unit+"','"+buyingPrice+"','"+sellingPrice+"','"+registrationDate+"','"+registrationDate+"')";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;

    }
    public String getLastestProductID(){
        String ID = "";
        try {
            String query = "SELECT MAX(productID) FROM `products`";
            ResultSet result = dbQuery.executeSelectQuery(query);
            if (result.next()) {
                ID=result.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        catch(NullPointerException e){
            System.out.println(e);
        }
        return ID;
    }
}

