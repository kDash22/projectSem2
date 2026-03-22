package salessystem;

public class Clerk extends User{
    //display items table
    //print receipt 
    //update quantity


    public Clerk(String fName, String lName, String password ){
        super(fName,lName,password);
    }

    @Override
    void displayRole() {
        System.out.println("Authority level : Clerk");
    }

    @Override
    public String toString(){
        String msg = super.toString();
        msg += "\nAuthority level : Clerk";
        return msg;
    }

}
