package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.Product;
import salessystem.model.SaleItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO related to handling database operations related to saleItem objects
public class SaleItemDAO {

    //inserts a saleItem into the database
    public void addSaleItem(Connection connection, int saleID, SaleItem saleItem) throws SQLException {

        String sql = "INSERT INTO sale_items (sale_id, product_id, quantity, subtotal) VALUES (?,?,?,?)";


        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, saleID);
        ps.setInt(2, saleItem.getProduct().getProductId());
        ps.setDouble(3, saleItem.getQuantity());
        ps.setDouble(4, saleItem.getSubtotal());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {

            saleItem.setSaleItemID(rs.getInt(1));//sets the sale item id
        }

    }

    //returns all the sale items from the database
    public List<SaleItem> getAllSaleItems(){

        List<SaleItem> saleItems = new ArrayList<>();

        String sql ="SELECT * FROM sale_items";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            while(rs.next()){

                //to retrieve the product needed to create the sale object from the product table
                ProductDAO pdao = new ProductDAO();

                Product product = pdao.getProductByProductID(rs.getInt("product_id"));
                saleItems.add(new SaleItem(
                        rs.getInt("sale_item_id"),
                        product,
                        rs.getDouble("quantity"),
                        rs.getDouble("subtotal")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while fetching sale items ! ",e);
        }
        return saleItems;
    }

    //returns all the sale items in the database
    public List<SaleItem> getSaleItemsBySaleID(int saleID){
        List<SaleItem> saleItems = new ArrayList<>();

        String sql = "SELECT * FROM sale_items WHERE sale_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, saleID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                //to retrieve the product needed for creating a SaleItem
                ProductDAO pdao = new ProductDAO();

                Product product = pdao.getProductByProductID(rs.getInt("product_id"));

                SaleItem saleItem = new SaleItem(
                        rs.getInt("sale_item_id"),
                        product,
                        rs.getDouble("quantity"),
                        rs.getDouble("subtotal")
                );
                saleItems.add(saleItem);
            }

        } catch(SQLException e){
            throw new RuntimeException("Error retrieving sale items", e);
        }

        return saleItems;
    }
}
