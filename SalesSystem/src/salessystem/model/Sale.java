package salessystem.model;

import salessystem.Global;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//represents a bill or a sale
public class Sale {

    private int saleID;
    private final Customer customer;
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

    //used to add sale items to the list of sale items
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

   //only for database usage
    public void setSaleID(int saleID){

        if (this.saleID != 0) { // it gets set by the database as saleID is the primary key and is set to auto increment
            throw new IllegalStateException("ID already set");
        }this.saleID = saleID;
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
        msg += "\nTime : "+ Global.dateTimeFormat(getDateTime());
        msg += "\nSale items : "+ Global.getListString(getSaleItems());
        msg += "\nTotal : "+getTotal();
        return msg;
    }


}
