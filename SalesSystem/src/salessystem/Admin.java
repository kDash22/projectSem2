package salessystem;

public class Admin extends User {
    
    public Admin(String firstName, String lastName, String password){
        super(firstName, lastName, password);
    }

    @Override
    void displayRole() {
        System.out.println("Authority level : Admin");
    }



}
