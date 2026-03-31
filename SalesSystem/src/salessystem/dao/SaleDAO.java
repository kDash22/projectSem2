package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//DAO related to handling database operations related to Sale objects
public class SaleDAO {

    //inserts a new sale into the database
    //use a transaction to ensure both sales and sale_items tables get the relevant records inserted
    public void addSale(Sale sale){

        String sql = "INSERT INTO sales (customer_id, total) VALUES (?,?)";

        try(Connection con = DBConnection.getConnection()){

            con.setAutoCommit(false);//transaction to ensure atomicity

            try (PreparedStatement ps1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {


                ps1.setInt(1, sale.getCustomer().getCustomerID());
                ps1.setDouble(2, sale.getTotal());


                ps1.executeUpdate();

                ResultSet rs1 = ps1.getGeneratedKeys();


                if (rs1.next()) {

                    int saleID = rs1.getInt(1);
                    sale.setSaleID(saleID); //sets the sale id

                    SaleItemDAO sidao = new SaleItemDAO(); // used for inserting the sale items into the database

                    //sale_items inserts
                    for (SaleItem item : sale.getSaleItems()) {
                        sidao.addSaleItem(con, saleID, item);
                    }
                }
                con.commit();

            } catch (SQLException e) {
                con.rollback(); //only commits if both actions are successfully
                throw new RuntimeException(" Error (transaction fail) occurred while adding Sales ! ",e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding sale ! ",e);
        }
    }

    //searches for a sale using sale id
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

                //to the items related to the saleID from the sale_items table
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

    //returns all the sales in the databse
    public List<Sale> getAllSales(){

        List<Sale> sales = new ArrayList<>();

        String sql ="SELECT * FROM sales";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            //to retrieve the customer needed to create the sale object from the customer table
            CustomerDAO cdao = new CustomerDAO();

            //to retrieve the items related to the saleID from the sale_items table
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

    //delete sale
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

}
