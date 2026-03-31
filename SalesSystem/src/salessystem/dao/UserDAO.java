package salessystem.dao;

import salessystem.database.DBConnection;
import salessystem.model.Admin;
import salessystem.model.Clerk;
import salessystem.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO related to handling database operations related to User objects
public class UserDAO {

    //create
    //uses a transaction to ensure insert and update both complete or none does
    public void addUser(User user)  {

        String insertSql = "INSERT INTO users (first_name,last_name,username,password,role) VALUES (?,?,?,?,?)";
        String updateSql = "UPDATE users SET username = ? WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection()){

            connection.setAutoCommit(false); //transaction to ensure atomicity

            try (PreparedStatement psInsert = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psUpdate = connection.prepareStatement(updateSql)){

                String tempUserName = "TEMP_"+System.nanoTime();/*

                -> temporary username set before configuring the
                username according to the lastname and the userID.
                -> System.nanoTime() ensures no 2 temporary usernames are the same

                */

                psInsert.setString(1, user.getFirstName());
                psInsert.setString(2, user.getLastName());
                psInsert.setString(3, tempUserName);
                psInsert.setString(4, user.getPassword());
                psInsert.setString(5, user.getRole());

                psInsert.executeUpdate();

                ResultSet rs = psInsert.getGeneratedKeys();

                    if (rs.next()) {

                        user.setUserID(rs.getInt(1));//sets the user id

                        user.createUserName();//generates the username

                        psUpdate.setString(1, user.getUserName());
                        psUpdate.setInt(2, user.getUserID());
                        psUpdate.executeUpdate();
                    }
                    connection.commit(); // end transaction

            }
            catch (SQLException e){
                connection.rollback(); //only commits if both actions are successfully
                throw new RuntimeException(" Error (transaction fail) occurred while adding user ! ",e);

            }

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while adding user ! ",e);
        }
    }

    //allows the searching of a user by the username
    public User getUserByUsername(String username){

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){

                if (rs.next()){

                    //initiates depending on the role
                    if (rs.getString("role").equals("Admin")){

                        return new Admin(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("username"),
                                rs.getString("password"));

                    }
                    if (rs.getString("role").equals("Clerk")){

                        return new Clerk(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("username"),
                                rs.getString("password"));
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching User.",ex);
        }
        return null;
    }

    //allows the searching of a user by the user id
    public User getUserByUserID(int userID){

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, userID);

            try(ResultSet rs = ps.executeQuery()){

                if (rs.next()){

                    //initiates depending on the role
                    if (rs.getString("role").equals("Admin")){

                        return new Admin(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("username"),
                                rs.getString("password"));

                    }
                    if (rs.getString("role").equals("Clerk")){

                        return new Clerk(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("username"),
                                rs.getString("password"));
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching User.",ex);
        }
        return null;
    }

    //returns all the users in the database
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
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                }

                if (rs.getString("role").equals("Clerk")){
                    users.add(new Clerk(
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
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

    //deletes an user
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

    //changes the password
    public void updatePassword(int userID, User user){

        String sql = "UPDATE users SET password = ? WHERE user_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getPassword());
            ps.setInt(2, userID);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating password ! ",e);
        }
    }

    //changes both first name and last name of a user
    public void updateName(int userID, User user){
        String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE user_id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, userID);

            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(" Error occurred while updating password ! ",e);
        }

    }
}
