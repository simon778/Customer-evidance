/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Payment {
    String note;
    double payment;
    Date date;
    
    public Payment(Date d, double payment, String note){
    this.note = note;
    this.payment= payment;
    this.date = d;
    }

   public String getNote(){return note;}
   public double getPayment(){return payment;}
   public Date getDate(){return date;}
}
