package salessystem.model;

import salessystem.GlobalMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private int saleID;
    private Customer customer;
    private LocalDateTime dateTime;
    private List<SaleItem> saleItems;
    private double total;


    //getters
    public int getSaleID() {
        return saleID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }



    public double getTotal() {return total;}

    public void addSaleItem(SaleItem saleItem){
        if (saleItem == null) {
            throw new IllegalArgumentException("SaleItem cannot be null");
        }
        saleItems.add(saleItem);
        this.total += saleItem.getSubtotal();
    }

    //for retrieving from the database use only
    public void setSaleItems(SaleItem saleItem){
        saleItems.add(saleItem);
    }


    //setters
    public void setTotal() {
        double value = 0;
        for (SaleItem saleItem : saleItems) {
            value += saleItem.getSubtotal();
        }
        this.total = value;
    }

   //only for database usage
    public void setSaleID(int saleID){
        if (this.saleID != 0) { // it gets set by the database as saleID is the primary key and is set to auto increment
            throw new IllegalStateException("ID already set");
        }this.saleID = saleID;
    }
    //only for database usage
    public void setDateTime(LocalDateTime dateTime){
        if (this.dateTime != null) { // it gets set by the database automatically
            throw new IllegalStateException("Date and time already set");
        }this.dateTime = dateTime;
    }


    //for making new sales, saleID is not assigned yet and will be done by the database
    public Sale(Customer customer){
        this.customer = customer;
        this.saleItems = new ArrayList<>(); //populated separately using addSaleItem()
        this.dateTime = LocalDateTime.now();

    }

    //for retrieving existing sales from the database
    public Sale(int saleID, Customer customer, LocalDateTime dateTime ,double total) {
        this.saleID = saleID;
        this.customer = customer;
        this.dateTime = dateTime;
        this.total = total;
        this.saleItems = new ArrayList<>(); // populated separately using addSaleItem()
    }

    @Override
    public String toString(){
        String msg = "\nSale ID : "+getSaleID();
        msg += "\nCustomer ID : "+getCustomer().getCustomerID();
        msg += "\nCustomer Name : "+getCustomer().getName();
        msg += "\nTime : "+ GlobalMethods.dateTimeFormat(getDateTime());
        msg += "\nSale items : "+GlobalMethods.getListString(getSaleItems());
        msg += "\nTotal : "+getTotal();
        return msg;
    }


}
