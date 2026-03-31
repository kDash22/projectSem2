package salessystem.dao;


import salessystem.database.DBConnection;
import salessystem.model.Product;
import salessystem.model.UnitType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO related to handling database operations related to Product objects
public class ProductDAO {

    //inserts a new product into the database
    public void addProduct(Product product){

        String sql = "INSERT INTO products ( product_name, price, stock, unit_type) VALUES (?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setDouble(3, product.getStock());
            ps.setString(4, product.getUnitType().name());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                product.setProductId(rs.getInt(1));//sets the product id
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding product ! ",e);
        }
    }

    //searches for a product using the product id
    public Product getProductByProductID(int productID){
        String sql = "SELECT * FROM products WHERE product_id = ? ";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, productID);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            UnitType.valueOf(rs.getString("unit_type")),
                            rs.getDouble("price"),
                            rs.getDouble("stock")

                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while fetching the product ! ",e);
        }
        return null;
    }

    //returns all the products in the database
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            while(rs.next()){

                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        UnitType.valueOf(rs.getString("unit_type")),
                        rs.getDouble("price"),
                        rs.getDouble("stock")
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while retrieving products ! ",e);
        }
        return products;
    }

    public void deleteProduct(int productID){
        String sql = "DELETE FROM products WHERE product_id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, productID);
                ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while deleting product ! ",e);
        }
    }

    public boolean updateProduct( int productId, Product product){

        String sql = "UPDATE products SET product_name = ?, price = ?, stock = ? WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,product.getProductName());
            ps.setDouble(2,product.getPrice());
            ps.setDouble(4, product.getStock());
            ps.setInt(4, productId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error while updating product ! ",e);
        }
    }

    public boolean updateProductStock( int productId, Product product){

        String sql = "UPDATE products SET stock = ? WHERE product_id = ?";


        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){


            ps.setDouble(1, product.getStock());
            ps.setInt(2, productId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error while updating stock of product ! ",e);
        }
    }

    public boolean updateProductPrice( int productId, Product product){

        String sql = "UPDATE products SET price = ? WHERE product_id = ?";


        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){


            ps.setDouble(1, product.getPrice());
            ps.setInt(2, productId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error while updating price of product ! ",e);
        }
    }

}
