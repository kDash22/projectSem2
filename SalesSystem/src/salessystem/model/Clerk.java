package salessystem.model;

//represents a new user type called Clerk
public class Clerk extends User{

    //Lacks the ability to manage users, only admin can do that
    //deleting records from tables also withdrawn from the user type clerk

    //for creating new Clerks only
    public Clerk(String firstName, String lastName, String password){

        super(firstName, lastName, password);
    }

    //for retrieving existing Clerks from the database only
    public Clerk(int userID,String firstName, String lastName, String userName, String password){

        super(userID,firstName,lastName,userName,password);
    }

    @Override
    public String getRole() {

        return "Clerk";
    }

    @Override
    public String toString(){
        String msg = super.toString();
        msg += "\nAuthority level : Clerk";
        return msg;
    }

}
