package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    public void addSale(Sale sale){
        String sql = "INSERT INTO sales (customer_id, total) VALUES (?,?)";

        try(Connection con = DBConnection.getConnection()){

            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps1.setInt(1, sale.getCustomer().getCustomerID());
            ps1.setDouble(2, sale.getTotal());


            ps1.executeUpdate();

            ResultSet rs1 = ps1.getGeneratedKeys();


            if(rs1.next()){
                int saleID = rs1.getInt(1);
                sale.setSaleID(saleID);

                SaleItemDAO sidao = new SaleItemDAO();

                //sale_items inserts
                for (SaleItem item : sale.getSaleItems()){
                    sidao.addSaleItem(con, saleID, item);
                }
            }
            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding sale ! ",e);
        }
    }

    public Sale getSaleBySaleID(int saleID){

        String sql = "SELECT * FROM sales WHERE sale_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,saleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //retrieving the customer needed to create the sale object from the customer table
                CustomerDAO cdao = new CustomerDAO();
                Customer customer = cdao.getCustomerByCustomerID(rs.getInt("customer_id"));

                 Sale sale = new Sale(
                        rs.getInt("sale_id"),
                        customer,
                        (LocalDateTime) rs.getObject("date"),
                        rs.getDouble("total")
                        );

                //fetching the items related to the saleID from the sale_items table
                SaleItemDAO sidao = new SaleItemDAO();
                List<SaleItem> saleItems= sidao.getSaleItemsBySaleID(saleID);

                for(SaleItem saleItem : saleItems){
                    sale.setSaleItems(saleItem);
                }

                return sale;

            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while retrieving sale ! ",e);
        }
        return null;
    }

    public List<Sale> getAllSales(){

        List<Sale> sales = new ArrayList<>();

        String sql ="SELECT * FROM sales";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            //retrieving the customer needed to create the sale object from the customer table
            CustomerDAO cdao = new CustomerDAO();
            //fetching the items related to the saleID from the sale_items table
            SaleItemDAO sidao = new SaleItemDAO();

            while(rs.next()){


                Customer customer = cdao.getCustomerByCustomerID(rs.getInt("customer_id"));
                int saleID = rs.getInt("sale_id");
                Sale sale = new Sale(
                        saleID,
                        customer,
                        (LocalDateTime) rs.getObject("date"),
                        rs.getDouble("total"));


                List<SaleItem> saleItems= sidao.getSaleItemsBySaleID(saleID);

                for(SaleItem saleItem : saleItems){
                    sale.setSaleItems(saleItem);
                }
                sales.add(sale);
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while fetching sales ! ",e);
        }
        return sales;
    }

    public void deleteSale(int saleID){

        String sql = "DELETE FROM sales WHERE sale_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, saleID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while deleting sale ! ",e);
        }

    }

    //no need for other update methods as other columns are permanent
    public boolean updateTotal(int saleID, double total){
        String sql = "UPDATE sales SET total = ? WHERE sale_id = ? ";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setDouble(1,total);
            ps.setInt(2,saleID);

            int rows = ps.executeUpdate();

            return rows>0;
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating total ! ",e);
        }

    }
}
