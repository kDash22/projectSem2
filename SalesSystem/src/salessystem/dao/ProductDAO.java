package salessystem.dao;


import salessystem.GlobalMethods;
import salessystem.database.DBConnection;
import salessystem.model.Product;
import salessystem.model.UnitType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void addProduct(Product product){

        String sql = "INSERT INTO product ( product_name, price, stock, unit_type) VALUES (?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setDouble(3, product.getStock());
            ps.setString(4, product.getUnitType().name());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                product.setProductId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding product ! ",e);
        }

    }

    public Product getProductByProductID(int productID){
        String sql = "SELECT * FROM product WHERE product_id = ? ";

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

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

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
        String sql = "DELETE FROM product WHERE product_id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, productID);
                ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while deleting product ! ",e);
        }
    }

    public boolean updateProduct( int productId, Product product){

        String sql = "UPDATE product SET product_name = ?, price = ?, stock = ? WHERE customer_id = ?";

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

        String sql = "UPDATE product SET stock = ? WHERE customer_id = ?";


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

        String sql = "UPDATE product SET price = ? WHERE customer_id = ?";


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



    public static void main(String[] args) {
        Product p1 = new Product("Rice packet 1kg", UnitType.PIECE, 200, 50);
        Product p2 = new Product("Sugar", UnitType.WEIGHT, 150, 70.5);
        Product p3 = new Product("cigarette packets", UnitType.PIECE, 3500, 50);
        System.out.println(p3);
        ProductDAO pdao = new ProductDAO();

        pdao.addProduct(p1);
        pdao.addProduct(p2);
        pdao.addProduct(p3);

        System.out.println("\n by id");
        System.out.println(pdao.getProductByProductID(3));

        System.out.println("\n everything");
        GlobalMethods.printList(pdao.getAllProducts());
    }
}
