package com.pos;

import com.pos.database.MySqlConnection;
import com.pos.database.QueryExecution;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Rafae Waqar Qazi
 */
public class Pos {
    Connection conn= null;
    private ResultSet result = null;
    private QueryExecution dbQuery;
    public Pos(){
        dbQuery = new QueryExecution();
        conn=MySqlConnection.getConnection();
    }
    public ResultSet getStockTableResult(){
        String query = "SELECT productID,article,sellingPrice FROM products";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getCartTableResult(){
        String query = "SELECT * FROM cart";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getResultFromStockTable(String productID){
        String query = "SELECT article,description,sellingPrice,quantity FROM products WHERE productID ='"+productID+"'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet search(String search){
        String query = "SELECT productID,article,sellingPrice FROM products WHERE article LIKE '%"+search+"%'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet filterByCategory(String filter){
        String query = "SELECT productID,article,sellingPrice FROM products WHERE category = '"+filter+"'";
        return dbQuery.executeSelectQuery(query);
    }
    public boolean addToCart(int RN,String productID,String article,String description,int sPrice,int quant,int disc,int total,String date,String transaction){
        String query = "INSERT INTO cart VALUES ('"+RN+"','"+productID+"','"+article+"','"+description+"','"+sPrice+"','"+quant+"','"+disc+"','"+total+"','"+date+"','"+transaction+"')";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    public int getReceiptNumber(){
        int RN = 0;
        try {
            String query = "SELECT MAX(ReceiptNo) FROM receipt";
            result = dbQuery.executeSelectQuery(query);
            if (result.next()) {
                RN=result.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return RN;
    }
    public int calculateTotal(){
        int total=0;
        try {
            String query = "SELECT SUM(Total) FROM cart";
            result = dbQuery.executeSelectQuery(query);
            if (result.next()) {
                total=result.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return total;
    }
    public void emptyCart(){
        String query = "DELETE FROM cart";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void removeFromCart(String productID){
        String query = "DELETE FROM cart WHERE ProductID ='"+productID+"'";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void updateProductQuantity(String productID,int productQuantity){
        String query = "UPDATE products SET quantity = '"+productQuantity+"' WHERE ProductID ='"+productID+"'";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void makeSale(){
        String query = "INSERT INTO sales (receiptNo,ProductID,Article,Description,Price,Quantity,Discount,Total,Date) SELECT ReceiptNo,ProductID,Article,Description,UnitPrice,Quantity,Discount,Total,Date FROM cart";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void updateReceipt(String ReceiptNo,String date,int total){
        String query = "INSERT INTO receipt VALUES('"+ReceiptNo+"','"+date+"','"+total+"')";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void printReceipt(){
        try{
            String report = "E:\\University's_Work\\5th Semester\\Advance Computer Programming\\Project\\PointOfSale\\src\\com\\pos\\report\\report1.jrxml";
            JasperDesign jd = JRXmlLoader.load(report);
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
