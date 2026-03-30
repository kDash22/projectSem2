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
        String insertSql = "INSERT INTO users (fname,lname,username,password,role) VALUES (?,?,?,?,?)";
        String updateSql = "UPDATE users SET username = ? WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection()){

            connection.setAutoCommit(false); //start a transaction to ensure atomicity

            try (PreparedStatement psInsert = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psUpdate = connection.prepareStatement(updateSql)){

                String temUserName = "TEMP_"+System.nanoTime();
                psInsert.setString(1, user.getFirstName());
                psInsert.setString(2, user.getLastName());
                psInsert.setString(3, temUserName);
                psInsert.setString(4, user.getPassword());
                psInsert.setString(5, user.getRole());

                psInsert.executeUpdate();

                ResultSet rs = psInsert.getGeneratedKeys();

                    if (rs.next()) {
                        user.setUserID(rs.getInt(1));
                        user.createUserName();

                        psUpdate.setString(1, user.getUserName());
                        psUpdate.setInt(2, user.getUserID());
                        psUpdate.executeUpdate();
                    }
                    connection.commit(); // end transaction

            }
            catch (SQLException e){
                connection.rollback();
                throw new RuntimeException(" Error (1) occurred while adding user ! ",e);

            }
        } catch (SQLException e) {
            throw new RuntimeException(" Error (2) occurred while adding user ! ",e);
        }
    }

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){
    //public Clerk(int userID,String firstName, String lastName, String userName, String password)
                if (rs.next()){
                    if (rs.getString("role").equals("Admin")){
                        Admin admin = new Admin(
                                rs.getInt("user_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("username"),
                                rs.getString("password"));
                        return admin;

                    }
                    if (rs.getString("role").equals("Clerk")){
                        Clerk clerk = new Clerk(
                                rs.getInt("user_id"),
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
    public User getUserByUserID(int userID){
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, userID);

            try(ResultSet rs = ps.executeQuery()){
                //public Clerk(int userID,String firstName, String lastName, String userName, String password)
                if (rs.next()){
                    if (rs.getString("role").equals("Admin")){
                        Admin admin = new Admin(
                                rs.getInt("user_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("username"),
                                rs.getString("password"));
                        return admin;

                    }
                    if (rs.getString("role").equals("Clerk")){
                        Clerk clerk = new Clerk(
                                rs.getInt("user_id"),
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
        String sql = "SELECT * FROM users";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){

                if (rs.getString("role").equals("Admin")){
                    users.add(new Admin(
                            rs.getInt("user_id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                }

                if (rs.getString("role").equals("Clerk")){
                    users.add(new Clerk(
                            rs.getInt("user_id"),
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

    public void deleteUser(int userID){

        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, userID);
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Error deleting user.",e );

        }
    }


    public boolean updatePassword(int userID, User user){
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";

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
        String sql = "UPDATE users SET fname = ?, lname = ? WHERE user_id = ?";

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
