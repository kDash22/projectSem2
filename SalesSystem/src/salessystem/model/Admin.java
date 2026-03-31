package salessystem.model;

//Represents a new user type called Admin
public class Admin extends User {

    //Has the ability to override and change the password of any user

    //for creating new Admins only
    public Admin(String firstName, String lastName, String password){

        super(firstName, lastName, password);
    }

    //for retrieving existing Admins from the database only
    public Admin(int userID,String firstName, String lastName, String userName, String password){

        super(userID,firstName,lastName,userName,password);
    }

    @Override
    public String getRole() {

        return "Admin";
    }

    @Override
    public String toString(){
        String msg = super.toString();
        msg += "\nAuthority level : Admin";
        return msg;
    }




}
