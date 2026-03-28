package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.Admin;
import salessystem.model.Clerk;
import salessystem.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user)  {
        String sql = "INSERT INTO employee (fname,lname,username,password,role) VALUES (?,?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){

            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    user.setUserID(rs.getInt(1));
                }

        }
        catch (SQLException e){
            throw new RuntimeException("Error adding user.");

        }
    }

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM employee WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){
    //public Clerk(int userID,String firstName, String lastName, String userName, String password)
                if (rs.next()){
                    if (rs.getString("role").equals("Admin")){
                        Admin admin = new Admin(
                                rs.getInt("employee_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("username"),
                                rs.getString("password"));
                        return admin;

                    }
                    if (rs.getString("role").equals("Clerk")){
                        Clerk clerk = new Clerk(
                                rs.getInt("employee_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("username"),
                                rs.getString("password"));
                        return clerk;
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching User.",ex);
        }
        return null;
    }

    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){

                if (rs.getString("role").equals("Admin")){
                    users.add(new Admin(
                            rs.getInt("employee_id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                }

                if (rs.getString("role").equals("Clerk")){
                    users.add(new Clerk(
                            rs.getInt("employee_id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                }


            }



        } catch (SQLException e) {
            throw new RuntimeException(" Error retrieving users ! ",e);
        }
        return users;
    }

    public void deleteUser(int empId){

        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Error deleting user.",e );

        }
    }
    //    firstName;
    //    lastName;
    //    password;
    public boolean updateUser(int userID,User user){
        String sql = "INSERT INTO employee(fname, lname, password) VALUES (?,?,?) WHERE employee_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, userID);

            int rows = ps.executeUpdate();
            return rows>0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating user ! ",e);
        }
    }

    public boolean updatePassword(int userID, User user){
        String sql = "INSERT INTO employee(password) VALUES (?) WHERE employee_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getPassword());
            ps.setInt(2, userID);

            int rows = ps.executeUpdate();
            return rows>0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating password ! ",e);
        }
    }

    public boolean updateName(int userID, User user){
        String sql = "INSERT INTO employee(fname, lname) VALUES (?,?) WHERE employee_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, userID);

            int rows = ps.executeUpdate();
            return rows>0;

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating password ! ",e);
        }

    }
}
