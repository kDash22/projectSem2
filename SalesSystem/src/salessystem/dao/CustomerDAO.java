package salessystem.dao;

import salessystem.GlobalMethods;
import salessystem.database.DBConnection;
import salessystem.model.Customer;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer){

        String sql = "INSERT INTO customer(name, contact) VALUES (?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,customer.getName());
            ps.setString(2,customer.getContactNumber());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                customer.setCustomerID(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding customer ! ",e);
        }

    }

    public Customer getCustomerByCustomerID(int customerID){
        String sql =  "SELECT * FROM customer WHERE customer_id = ?";

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

    public List<Customer> getAllCustomers(){

        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

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

    public void deleteCustomer(int customerID){
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,customerID);

            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(" Error occurred while deleting customer ! ",e);
        }
    }

    public boolean updateCustomer( int customerID, Customer customer){

        String sql = "UPDATE customer SET name = ?, contact = ? WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,customer.getName());
            ps.setString(2,customer.getContactNumber());
            ps.setInt(3, customerID);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error while updating customer ! ",e);
        }
    }

    public boolean updateCustomerContact(int customerID, Customer customer){
        String sql = "UPDATE customer SET contact = ? WHERE customer_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){
          ps.setString(1,customer.getContactNumber());
          ps.setInt(2, customerID);

          int rows = ps.executeUpdate();
          return rows>0 ;
        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating contact number ! ",e);
        }

    }

    static void main(String[] args) {

        Customer c1 = new Customer("Tywin Lannister", "1234567890");
        Customer c2 = new Customer("Olenna Tyrell","1234567890");
        Customer c3 = new Customer("John snow","1234567890");

        CustomerDAO cdao = new CustomerDAO();
        cdao.addCustomer(c1);
        cdao.addCustomer(c2);
        cdao.addCustomer(c3);

        System.out.println(" The king in the north");
        System.out.println(cdao.getCustomerByCustomerID(3));
        cdao.deleteCustomer(2);
        System.out.println();
        System.out.println("all of them");
        GlobalMethods.printList(cdao.getAllCustomers());

        System.out.println("\n"+cdao.updateCustomer(3 ,new Customer("Aemon Targareyan","1234567890")));
        System.out.println("\n"+cdao.updateCustomerContact(1, new Customer("coming through","0987654321")));

        System.out.println(" new and updated");
        GlobalMethods.printList(cdao.getAllCustomers());


    }
}

