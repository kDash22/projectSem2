package salessystem;

public class Product {
    private int productId;
    private static int nextProductId=1;

    private String productName;
    private double price;
    private int quantity;

    public Product(String productName, double price, int quantity){
        productId = nextProductId++;
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
    }

    public String toString(){
        String msg ="\nProduct ID : "+getProductId();
        msg += "\nProduct Name : "+getProductName();
        msg += "\nPrice : "+getPrice();
        msg += "\nQuantity : "+getQuantity();
        return msg;
    }

    //setters
    public void setProductName(String productName){
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        this.productName = productName;
    }
    public void setPrice(double price) throws IllegalArgumentException{
        if (price<=0) {
            throw new IllegalArgumentException("Price cannot be negative or zero!");
        }
        this.price=price;
    }
    public void setQuantity(int quantity) throws IllegalArgumentException{
        if (quantity<0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity=quantity;
    }

    //getters
    public int getProductId(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }

}
