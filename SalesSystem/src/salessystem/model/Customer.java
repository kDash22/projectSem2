package salessystem.model;

//Represents a Customer
public class Customer {
    private int customerID;
    private String name;
    private String contactNumber;

    //setters
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setCustomerID(int customerID){

        if (this.customerID != 0) {
            // it gets set by the database as customerID is the primary key and is set to auto increment
            throw new IllegalStateException("ID already set");
        }
        this.customerID = customerID;
    }



    public void setContactNumber(String contactNumber) {
        if (validateContactNumber(contactNumber))
        this.contactNumber = contactNumber;
    }

    //getters

    public String getContactNumber() {
        return contactNumber;
    }

    public String getName() {
        return name;
    }

    public int getCustomerID() {
        return customerID;
    }

    //for new customers, the databse will set the customer ID with autoincrement
    public Customer(String name, String contactNumber){

        setName(name);
        setContactNumber(contactNumber);

    }

    //for retrieving existing customers from the database
    public Customer(int customerID, String name, String contactNumber){
        this(name, contactNumber);
        setCustomerID(customerID);
    }

    @Override
    public String toString(){

        String msg = "\nName : "+getName();
        msg += "\nCustomer ID: "+getCustomerID();
        msg += "\nContact Number : "+getContactNumber();
        return msg;
    }

    //checks if a String variable is isNumericOnly
    public static boolean isNumericOnly(String value) {
        return value != null && value.matches("\\d+");
    }

    //Instance variable validation methods
    public void validateName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(" Customer name cannot be empty ! ");
        }
    }

    public static boolean validateContactNumber(String contactNumber){

        if (contactNumber == null || contactNumber.trim().isEmpty()){
            return false;
        }
        if (contactNumber.length() != 10) {
            return false;
        }
        return isNumericOnly(contactNumber);

    }











}
