package salessystem.testing;

import salessystem.model.Admin;
import salessystem.model.Clerk;
import salessystem.GlobalMethods;
import salessystem.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestUserDAO {

    public void addUser(User user)  {
        String sql = "INSERT INTO employee (employee_id,fname,lname,user_name,password,role) VALUES (?,?,?,?,?,?)";

        try (Connection connection = TestDBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, user.getEmployeeId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());

            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Error adding user.");

        }
    }

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM employee WHERE user_name = ?";

        try (Connection connection = TestDBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    if (rs.getString("role").equals("Admin")){
                        Admin admin = new Admin(
                                rs.getInt("employee_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("password"));
                        return admin;

                    }
                    if (rs.getString("role").equals("Clerk")){
                        Clerk clerk = new Clerk(
                                rs.getInt("employee_id"),
                                rs.getString("fname"),
                                rs.getString("lname"),
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

        try(Connection con = TestDBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){

                if (rs.getString("role").equals("Admin")){
                    users.add(new Admin(
                            rs.getInt("employee_id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("password")
                    ));
                }

                if (rs.getString("role").equals("Clerk")){
                    users.add(new Clerk(
                            rs.getInt("employee_id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
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
        try (Connection connection = TestDBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Error deleting user.",e );

        }




    }

    public static void main(String[] args) {
        Admin a1 = new Admin(1,"Kalana", "Dasanayaka", "Maekar2295!@#");
        Admin a2 = new Admin(2, "Thevindu", "Bandara", "Thevindu1234!@#");
        Admin a3 = new Admin(3, "Baelor", "Breakspear", "Baelor1234!@#");

        Clerk c1 = new Clerk(4, "Loras", "Tyrell", "Tyrell1234!@#");
        Clerk c2 = new Clerk(5, "Jaime", "Lannister", "Lannister1234!@#");

        TestUserDAO u1 = new TestUserDAO();
        u1.addUser(a1);
        u1.addUser(a2);
        u1.addUser(a3);
        u1.addUser(c1);
        u1.addUser(c2);

        System.out.println(u1.getUserByUsername("dasanayaka1"));;
        System.out.println("\n Here you go! ");
        GlobalMethods.printList(u1.getAllUsers());

        u1.deleteUser(1);
        System.out.println("\n you gone homie ");
        GlobalMethods.printList(u1.getAllUsers());

    }


}
