/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos;

import com.pos.database.MySqlConnection;
import com.pos.database.QueryExecution;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Rafae Waqar Qazi
 */
public class Inventory {
    ResultSet result = null;
    private QueryExecution dbQuery;
    public Inventory(){
        dbQuery = new QueryExecution();
    }
    
    public ResultSet getInventoryTableResult(){
        String query = "SELECT productID,article,category,description,quantity,unit,buyingPrice,sellingPrice FROM products";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getOverAllSaleTableResult(){
        String query = "SELECT * FROM sales";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getResultFromOverAllSaleTable(String saleID){
        String query = "SELECT * FROM sales WHERE SaleID ='"+saleID+"'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getStockInOutTableResult(){
        String query = "SELECT ProductID, Article, SUM(StockIn) AS Total_IN,SUM(StockOut) AS Total_Out FROM `stockinout` GROUP BY ProductID";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getResultForStockInOutDetailsTable(String productID){
        String query = "SELECT * FROM stockinout WHERE ProductID = '"+productID+"' GROUP BY StockID";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet getResultFromInventoryTable(String productID){
        String query = "SELECT * FROM products WHERE productID ='"+productID+"'";
        return dbQuery.executeSelectQuery(query);
    }
    public void updateExistingProducts(String productID,String article,String category,String description,int quantity,String unit,int buyingPrice,int sellingPrice,String registerationDate,LocalDate dateUpdated){
        String query = "UPDATE products SET article ='"+article+"', category ='"+category+"',description = '"+description+"', quantity='"+quantity+"',unit='"+unit+"', buyingPrice= '"+buyingPrice+"'";
                query+=", sellingPrice = '"+sellingPrice+"', registrationDate= '"+registerationDate+"', dateUpdated ='"+dateUpdated+"' WHERE productID = '"+productID+"'";
        dbQuery.executeUpdateDeleteInsertQuery(query);

    }
    public ResultSet searchByProductID(String search){
        String query = "SELECT productID,article,category,description,quantity,unit,buyingPrice,sellingPrice FROM products WHERE productID LIKE '%"+search+"%'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet searchByArticle(String search){
        String query = "SELECT productID,article,category,description,quantity,unit,buyingPrice,sellingPrice FROM products WHERE article LIKE '%"+search+"%'";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet searchStockByProductID(String search){
        String query = "SELECT ProductID, Article, SUM(StockIn) AS Total_IN,SUM(StockOut) AS Total_Out FROM stockinout WHERE ProductID LIKE '%"+search+"%' GROUP BY ProductID";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet searchStockByArticle(String search){
        String query = "SELECT ProductID, Article, SUM(StockIn) AS Total_IN,SUM(StockOut) AS Total_Out FROM stockinout WHERE Article LIKE '%"+search+"%' GROUP BY ProductID";
        return dbQuery.executeSelectQuery(query);
    }
    
    public ResultSet filterByCategory(String filter){
        String query = "SELECT productID,article,category,description,quantity,unit,buyingPrice,sellingPrice FROM products WHERE category = '"+filter+"'";
        return dbQuery.executeSelectQuery(query);
    }
    public boolean deleteProduct(String productID){
        String query = "DELETE FROM products WHERE productID ='"+productID+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    public int getProductQuantity(String productID){
        int quantity = 0;
        try {
            String query = "SELECT quantity FROM products WHERE productID ='"+productID+"'";
            result = dbQuery.executeSelectQuery(query);
            if (result.next()) {
                quantity=result.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return quantity;
    }
    public void addStockIn(String productID,String article,int stockIn,String date){
        String query = "INSERT INTO stockinout (productID,article,stockIn,Date) VALUES ('"+productID+"','"+article+"','"+stockIn+"','"+date+"')";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public void addStockOut(){
        String query = "INSERT INTO stockinout (ProductID,Article,StockOut,Date,ReceiptNo,TransactionType) SELECT ProductID,Article,Quantity,Date,ReceiptNo,transaction FROM cart";
        dbQuery.executeUpdateDeleteInsertQuery(query);
    }
    public boolean emptySale(){
        String query = "DELETE FROM sales";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    public boolean deleteSale(String saleID){
        String query = "DELETE FROM sales WHERE saleID ='"+saleID+"'";
        if(dbQuery.executeUpdateDeleteInsertQuery(query))
           return true;
        else 
           return false;
    }
    public ResultSet searchSaleByRN(String search){
        String query = "SELECT * FROM sales WHERE ReceiptNo LIKE '%"+search+"%'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet searchSaleByDate(String search){
        String query = "SELECT * FROM sales WHERE Date LIKE '%"+search+"%'";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getGroupSaleTableResult(){
        String query = "SELECT * FROM receipt";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getAllResultForGroupSaleDetailsTable(String receiptNo){
        String query = "SELECT * FROM sales WHERE ReceiptNo = '"+receiptNo+"' GROUP BY SaleID";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getDailyResultForGroupSaleDetailsTable(String date){
        String query = "SELECT * FROM sales WHERE date = '"+date+"' GROUP BY SaleID";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getWeeklyResultForGroupSaleDetailsTable(String week,String year){
        String query = "SELECT * FROM sales WHERE Date BETWEEN DATE_ADD('"+year+"-01-01',INTERVAL "+week+" WEEK) AND DATE_ADD(DATE_ADD('"+year+"-01-01',INTERVAL "+week+" WEEK),INTERVAL 1 WEEK)";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet getMonthlyResultForGroupSaleDetailsTable(String year,String month){
        String query = "SELECT * FROM sales WHERE Date BETWEEN '"+year+"-"+month+"-01' AND DATE_ADD('"+year+"-"+month+"-01',INTERVAL 1 MONTH)";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet weeklyUpdateSaleTable(){
        String query = "SELECT YEAR(Date) AS Year, WEEK(Date) AS Week, SUM(Total) AS Total FROM sales GROUP BY Week";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet monthlyUpdateSaleTable(){
        String query = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(Total) AS Total FROM sales GROUP BY Month";
        return dbQuery.executeSelectQuery(query);
    }
    public ResultSet dailyUpdateSaleTable(){
        String query = "SELECT DATE(Date) AS Date, SUM(Total) AS TotalDailySale FROM sales GROUP BY date";
        return dbQuery.executeSelectQuery(query);
    }
    
    public void dailyGraph(){
        try {
            String query = "SELECT DATE(Date) AS Date, SUM(Total) AS TotalDailySale FROM sales GROUP BY date";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(MySqlConnection.getConnection(),query);
            JFreeChart chart = ChartFactory.createBarChart("Daily Sale Graph", "Date", "Sale", dataset, PlotOrientation.VERTICAL,false, true, true);
            ChartFrame frame = new ChartFrame("Daily Sale",chart);
            frame.setSize(400,400);
            frame.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void weeklyGraph(){
        try {
            String query = "SELECT WEEK(Date) AS Week, SUM(Total) AS Total FROM sales GROUP BY Week";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(MySqlConnection.getConnection(),query);
            JFreeChart chart = ChartFactory.createBarChart("Weekly Sale Graph", "Week", "Sale", dataset, PlotOrientation.VERTICAL,false, true, true);
            ChartFrame frame = new ChartFrame("Weekly Sale",chart);
            frame.setSize(400,400);
            frame.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void monthlyGraph(){
        try {
            String query = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(Total) AS Total FROM sales GROUP BY Month";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(MySqlConnection.getConnection(),query);
            JFreeChart chart = ChartFactory.createBarChart("Monthly Sale Graph", "Month", "Sale", dataset, PlotOrientation.VERTICAL,false, true, true);
            ChartFrame frame = new ChartFrame("Monthly Sale",chart);
            frame.setSize(400,400);
            frame.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void overAllSaleGraph(){
        try {
            String query = "SELECT productID, Quantity From sales GROUP BY productID";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(MySqlConnection.getConnection(),query);
            JFreeChart chart = ChartFactory.createBarChart("Sale of Each Product", "Product ID", "Sale", dataset, PlotOrientation.VERTICAL,false, true, true);
            ChartFrame frame = new ChartFrame("Each Products Sale",chart);
            frame.setSize(400,400);
            frame.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public ResultSet calculateProfit(){
        String query = "SELECT SUM((SELECT buyingPrice FROM products p WHERE p.productID = s.productID)*Quantity) AS TotalExpense, SUM(Quantity*Price) AS ExpectedReturn, SUM(Total) AS ActualReturn FROM sales s";
        return dbQuery.executeSelectQuery(query);
    }
}
