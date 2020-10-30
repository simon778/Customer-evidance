/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import java.util.Date;

/**
 *
 * @author Hudek
 */
public class Customer {

    private int ID;
    private String firstName;
    private String lastName;
    private String address;
    private Date contractDate; 
    private int contractLength; // length of contract in months
    private double amountInsured;
    private Insurance insuranceType;

    public Customer(String firstName, String surrname) {
        this.firstName = firstName;
        this.lastName = surrname;
    }

    public Customer(int ID, String firstName, String lastName) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, String addres, Date contract_date, int contract_length, double amountInsured,  Insurance insuranceType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = addres;
        this.contractDate = contract_date;
        this.contractLength = contract_length;
        this.amountInsured = amountInsured;
        this.insuranceType = insuranceType;

    }
   
    public Customer(int ID, String firstName, String lastName, String address, Date contractDate, int contractLength, double amountInsured, Insurance insuranceType) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contractDate = contractDate;
        this.contractLength = contractLength;
        this.amountInsured = amountInsured;
        this.insuranceType = insuranceType;

    }

    public Insurance getInsuranceType() {
        return insuranceType;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getID() {
        return this.ID;
    }

    public String getAddres() {
        return this.address;
    }

    public Date getContractDate() {
        return this.contractDate;
    }

    public int getContractLength() {
        return this.contractLength;
    }

    public double getAmountInsured() {
        return this.amountInsured;
    }
}
