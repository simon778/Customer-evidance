/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnect;

import java.sql.*;

/**
 *
 * @author Hudek
 */
public class DBConnect {
    protected Connection con;
    protected Statement st;
    protected ResultSet rs;
    
    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(!checkDBExists("customer_evidence"))
            {
                createDatabase("customer_evidence");
            }   
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_evidence", "root", "");
            st= con.createStatement();
            
        }catch(Exception ex){
            System.out.println("Error: "+ ex);
        }    
        }
    private void createDatabase(String name){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //Register JDBC Driver
             System.out.println("Database does not exitst. \nCreating connection...");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", ""); //Open a connection
               System.out.println("Creating database...");
               st = con.createStatement();
               String query = "CREATE DATABASE "+name;
               st.executeUpdate(query);
               System.out.println("Database created successfully..."); 
               
               query="USE "+name+";";
               st.executeUpdate(query);
               System.out.println("Database "+name+" selected...");
               
               //creating tables
               //create table customer evidance
               String query_creare_customers = "CREATE TABLE CUSTOMER" +
                    "(customer_id INTEGER not NULL AUTO_INCREMENT, " +
                   " first_name VARCHAR(255), " + 
                   " last_name VARCHAR(255), " + 
                   "address VARCHAR(255), " +
                   "date_of_contract DATE, "+
                   "duration_of_contractin INTEGER,"+
                    "amount_insured DECIMAL,"+
                    "insurence_type VARCHAR(255),"+
                    "PRIMARY KEY (customer_id))"; 
               st.executeUpdate(query_creare_customers);
               System.out.println("Table customer created successfully..."); 
               String query_create_payments="CREATE table payments(customer_id integer, date DATETIME DEFAULT CURRENT_TIMESTAMP, payment decimal(10,2), note varchar(255), PRIMARY KEY (customer_id, date), FOREIGN KEY(customer_id) references customer(customer_id) ON DELETE CASCADE)";
               st.executeUpdate(query_create_payments);
               System.out.println("Table payments created successfully..."); 
           
        }
        catch(SQLException se){
           //Handle errors for JDBC
          se.printStackTrace();
        }catch(Exception e){
          //Handle errors for Class.forName
          e.printStackTrace();
        }finally{
      //finally block used to close resources
      try{
         if(st!=null)
            st.close();
      }catch(SQLException se2){
          se2.printStackTrace();
      }
      try{
         if(con!=null)
            con.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Resources closed!");
    }
    
    private boolean checkDBExists(String dbName){

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); //Register JDBC Driver

        System.out.println("Creating connection...");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", ""); //Open a connection

        ResultSet resultSet = con.getMetaData().getCatalogs();

        while (resultSet.next()) {

          String databaseName = resultSet.getString(1);
            if(databaseName.equals(dbName)){
                return true;
            }
        }
        resultSet.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }

    return false;
}
    
}

