package salessystem.model;

public class SaleItem {

    private int saleItemID;
    private  Product product;
    private double quantity;
    private double subtotal;

    //setters
    public void setSaleItemID(int saleItemID){
        if (this.saleItemID != 0) { // it gets set by the database as saleItemID is the primary key and is set to auto increment
            throw new IllegalStateException("ID already set");
        }this.saleItemID = saleItemID;
    }

    //getters
    public double getSubtotal() {
        return subtotal;
    }

    public double getUnitPrice() {
        return getProduct().getPrice();
    }

    public double getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getSaleItemID() {
        return saleItemID;
    }

    @Override
    public String toString(){
        String msg = "\nSale Item ID : "+ getSaleItemID();
        msg += "\n" + getProduct() + "\n";

        if (product.getUnitType() == UnitType.PIECE){
            msg += "\nUnit Price : "+ getUnitPrice();
        }

        if (product.getUnitType() == UnitType.WEIGHT){
            msg += "\nUnit Price (per 1kg): "+ getUnitPrice();
        }
        msg += "\nQuantity : " + getQuantity();
        msg += "\nSubtotal : " + getSubtotal();
        return msg;

    }

    //for creating new salesItems to be entered into the database
    public SaleItem(Product product, double quantity){
        validateQuantity(product,quantity);
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    //for retrieving existing salesItems from the database
    public SaleItem(int saleItemID, Product product, double quantity, double subtotal) {
        this.saleItemID = saleItemID;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public void validateQuantity(Product product,double quantity){
        if (product.getUnitType() == UnitType.PIECE && quantity %1 != 0){
            throw new IllegalArgumentException(" Quantity must be a whole number for PIECE type products");
        }
        if (quantity > product.getStock()){
            throw new IllegalArgumentException(" Stock cannot be lower than the ordered quantity ! ");
        }
    }
}
