package salessystem.model;

import salessystem.GlobalMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private int saleID;
    private Customer customer;
    private LocalDateTime date;
    private List<SaleItem> saleItems;

    //getters
    public int getSaleID() {
        return saleID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void addSaleItem(SaleItem saleItem){
        saleItems.add(saleItem);
    }

    public double calculateTotal(){
        double total = 0;
        for (SaleItem saleItem : saleItems) {
            total += saleItem.getSubtotal();
        }
        return total;
    }

    void setSaleID(int saleID){
        this.saleID = saleID;
    }

    public Sale(Customer customer){
        this.saleID = 0;
        this.customer = customer;
        this.date = LocalDateTime.now();
        this.saleItems = new ArrayList<>();

    }

    @Override
    public String toString(){
        String msg = "\nSale ID : "+getSaleID();
        msg += "\nCustomer NIC : "+getCustomer().getNic();
        msg += "\nCustomer Name : "+getCustomer().getName();
        msg += "\nTime : "+ GlobalMethods.dateTimeFormat(getDate());
        msg += "\nSale items : "+getSaleItems();
        msg += "\nTotal : "+calculateTotal();
        return msg;
    }


}
