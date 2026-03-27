package salessystem.model;

public class SaleItem {

    private final int saleItemID = 0 ;
    private final Product product;
    private final double quantity;

    void setSaleItemID(){

    }
    //getters
    public double getSubtotal() {
        return getUnitPrice() * quantity;
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

    public SaleItem(Product product, double quantity){
        validateQuantity(product,quantity);
        this.product = product;
        this.quantity = quantity;
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
