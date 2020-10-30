/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnect;

import Customer.Customer;
import Customer.Insurance;
import Customer.Payment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Hudek
 */
public class Evidence_DBConnect extends DBConnect{
    
    public Evidence_DBConnect(){
        super();
    }
    public LinkedList<Payment> getPayment(int id){
        LinkedList<Payment> payments = new LinkedList<Payment>();
        try{      
            String query ="Select * from payments where customer_id=" + id;
            Payment payment;
            rs = st.executeQuery(query);  
             while(rs.next()){
                    String note = rs.getString("note");
                    
                    Date date = rs.getDate("date");
                    double paymentAmount = rs.getDouble("payment");
                    payment= new Payment(date, paymentAmount, note);
                    payments.add(payment);
            }
      } catch(Exception ex){
                System.out.println("Error : " + ex);
            } 
     return payments;
    }
    /*
    * Get data from the database
    */
    public LinkedList<Customer> getCustomers(){
               LinkedList<Customer> customers = new LinkedList<Customer>();
            try{
                String query ="Select * from customer";
                rs = st.executeQuery(query);   
                while(rs.next()){
                    int ID = rs.getInt("customer_id");
                    String first_name = rs.getString("first_name");
                    String last_name  = rs.getString("last_name");
                    String address  = rs.getString("address");
                    Date date_of_contract = rs.getDate("date_of_contract");
                    int duration_of_contractin  = rs.getInt("duration_of_contractin");
                    double amount_insured  = rs.getDouble("amount_insured");
                    Insurance insurence_type;
                    if(!rs.getString("insurence_type").equals("null"))
                        insurence_type  = Insurance.valueOf(rs.getString("insurence_type"));
                    else
                        insurence_type = Insurance.UNKNOW;
                    
                    Customer customer = new Customer(ID, first_name, last_name, address, date_of_contract, duration_of_contractin, amount_insured, insurence_type);
                    customers.add(customer);
                }            
            }
            catch(Exception ex){
                System.out.println("Error : " + ex);
            } 
           return customers;      
}
    
    public void updateCustomer(int ID, Customer customer){
               try {
                String firstName = customer.getFirstName();
                String lastName = customer.getLastName();
                String address= customer.getAddres();
                Date contract_date= customer.getContractDate();
                int contract_length = customer.getContractLength();
                double amountInsured = customer.getAmountInsured();
                String insuranceType = customer.getInsuranceType().name();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date =dateFormat.format(contract_date);
                String query = "UPDATE customer SET " + 
                                "first_name='" + firstName + "' , " + 
                                "last_name='" + lastName +   "' , " + 
                                "address='" + address +  "' , " + 
                                "date_of_contract='" +  date +  "', " + 
                                "duration_of_contractin='" + contract_length +  "', " + 
                                "amount_insured='" +  amountInsured + "', " +
                                "insurence_type='" +  insuranceType + "' " +
                                      " WHERE customer_id=" + ID ;          
                        st.executeUpdate(query);
               
            } catch (Exception ex) {
               System.out.println("Error(Evidence_DCConnect.setData) : " + ex);
            }
    }
    /*
    *Insert data into database
    */
    public void insertCustomer(Customer customer){
            try {
                String firstName = customer.getFirstName();
                String lastName = customer.getLastName();
                String address= customer.getAddres();
                Date contract_date= customer.getContractDate();
                int contract_length = customer.getContractLength();
                double amountInsured = customer.getAmountInsured();
                String insuranceType = customer.getInsuranceType().name();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date =dateFormat.format(contract_date);
                String query = "Insert into customer (first_name, last_name, address, date_of_contract, duration_of_contractin, amount_insured, insurence_type) values "
                        + "     ('" + 
                                firstName + "', '" + 
                                lastName + "', '" + 
                                address + "', '" + 
                                date + "', '" + 
                                contract_length + "', '" + 
                                amountInsured + "', '" + 
                                insuranceType + "') " ;
                    
                        st.executeUpdate(query);
              
            } catch (Exception ex) {
               System.out.println("Error(Evidence_DCConnect.setData) : " + ex);
            }
        }
    
    public void deleteCustomer(int ID){
        try{
            String query = "Delete from customer WHERE customer_id=" + ID;
             st.executeUpdate(query);
        }
        catch(Exception ex){
             System.out.println("Message: " + ex);
        }
    }
    
    public void insertPayment(int ID, double payment, String note){
        try{
               String query= "Insert into payments (customer_id, payment, note) values (" +
               ID + ", " +
               payment + ", " +
                "'" +  note + "'); " ;
                st.executeUpdate(query);
               
            } catch (Exception ex) {
               System.out.println("Error(Evidence_DCConnect.insertPayment) : " + ex);
            }
    }
}
        
