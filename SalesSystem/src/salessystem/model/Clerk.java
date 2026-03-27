package salessystem.model;

public class Clerk extends User{
    //display items table
    //print receipt 
    //update quantity


    public Clerk(int employeeID,String fName, String lName, String password ){
        super(employeeID,fName,lName,password);
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
