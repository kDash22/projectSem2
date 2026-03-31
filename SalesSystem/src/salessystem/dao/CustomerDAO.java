package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO related to handling database operations related to Customer objects
public class CustomerDAO {

    //inserts a customer into the database
    public void addCustomer(Customer customer){

        String sql = "INSERT INTO customers(name, contact) VALUES (?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,customer.getName());
            ps.setString(2,customer.getContactNumber());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                customer.setCustomerID(rs.getInt(1)); //sets the customer id
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding customer ! ",e);
        }

    }

    //search for customer using the customer id
    public Customer getCustomerByCustomerID(int customerID){

        String sql =  "SELECT * FROM customers WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1,customerID);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return (new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("name"),
                            rs.getString("contact")
                            ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while fetching the customer ! ",e);
        }
        return null;
    }

    //returns all the customers in the database
    public List<Customer> getAllCustomers(){

        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while retrieving customers ! ",e);
        }
        return customers;
    }

    //deletes a customer
    public void deleteCustomer(int customerID){
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,customerID);

            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(" Error occurred while deleting customer ! ",e);
        }
    }

    //allows the change of customer name and customer contact number
    //helpful if a customer wants to transfer his account to someone
    public void updateCustomer(int customerID, Customer customer){

        String sql = "UPDATE customers SET name = ?, contact = ? WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,customer.getName());
            ps.setString(2,customer.getContactNumber());
            ps.setInt(3, customerID);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(" Error while updating customer ! ",e);
        }
    }

    //allows the change of only the contact number
    public void updateCustomerContact(int customerID, Customer customer){
        String sql = "UPDATE customers SET contact = ? WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){
          ps.setString(1,customer.getContactNumber());
          ps.setInt(2, customerID);

          ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating contact number ! ",e);
        }

    }


}

