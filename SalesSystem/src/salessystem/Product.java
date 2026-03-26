package salessystem;

public class Product {
    private final int productId;
    private static int nextProductId = 1;

    private String productName;
    private double price;
    private double stock;
    private UnitType unitType;

    public Product(String productName, UnitType unitType, double price, double stock) {

        setProductName(productName);
        setUnitType(unitType);
        setPrice(price);
        setStock(stock);
        productId = nextProductId++;
    }

    public String toString() {
        String msg = "\nProduct ID : " + getProductId();
        msg += "\nProduct Name : " + getProductName();
        msg += "\nPrice : " + getPrice();
        if (getUnitType() == UnitType.WEIGHT) {
            msg += "\nStock Amount (kg) : " + getStock();
        }
        if (getUnitType() == UnitType.PIECE) {
            msg += "\nStock Amount (units) : " + getStock();
        }

        return msg;
    }

    //setters
    public void setProductName(String productName) {
        validateProductName(productName);
        this.productName = productName;
    }

    public void setUnitType(UnitType unitType) {
        validateUnitType(unitType);
        this.unitType = unitType;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        validatePrice(price);
        this.price = price;
    }

    public void setStock(double stock) throws IllegalArgumentException {
        validateStock(stock);
        this.stock = stock;
    }

    //getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getStock() {
        return stock;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    //validations
    public void validateProductName(String productName) {
        if (productName == null || productName.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty!");
    }

    public void validateUnitType(UnitType unitType) {
        if (unitType == null)
            throw new IllegalArgumentException("Unit type cannot be null!");
    }


    public void validatePrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than zero!");
    }

    public void validateStock(double stock) {
        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative!");

        if (unitType == UnitType.PIECE && stock % 1 != 0)
            throw new IllegalArgumentException("Stock must be whole number for PIECE type products");
    }
}