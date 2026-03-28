package salessystem.model;

public class Clerk extends User{
    //display items table
    //print receipt 
    //update quantity

    //for creating new Clerks only
    public Clerk(String firstName, String lastName, String password){
        super(firstName, lastName, password);
    }

    //for retrieving existing Clerks from the database only
    public Clerk(int userID,String firstName, String lastName, String userName, String password){
        super(userID,firstName,lastName,userName,password);
    }


    @Override
    void displayRole() {
        System.out.println("Authority level : Clerk");
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
