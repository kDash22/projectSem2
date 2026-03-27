package salessystem.model;

public class Admin extends User {
    //override password of user 
    public Admin(int employeeID,String firstName, String lastName, String password){

        super(employeeID,firstName, lastName, password);
    }

    @Override
    void displayRole() {
        System.out.println("Authority level : Admin");
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public void overridePassword(Clerk user,String password){
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Invalid password : Must contain at least one uppercase letter, lowercase letter and a number!");
        } 
        user.setPassword(password);
    }

    @Override
    public String toString(){
        String msg = super.toString();
        msg += "\nAuthority level : Admin";
        return msg;
    }




}
