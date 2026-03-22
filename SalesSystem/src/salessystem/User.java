package salessystem;

public abstract class User {
    protected String firstName;
    protected String lastName;

    private static int nextEmployeeId = 1;
    private final int employeeId;
    
    
    private final String userName;
    private String password;

    abstract void displayRole();

    //getters
    public int getEmployeeId() {
        return employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }

    //setters
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String currentPassword, String newPassword){
        if (!currentPassword.equals(getPassword())) {
            throw new IllegalArgumentException("Incorrect current password!!!");
            
        }
        if (!passwordCheck(newPassword)) {
            throw new IllegalArgumentException("Invalid password : Must contain at least one uppercase letter, lowercase letter and a number");
        } 
        setPassword(newPassword);
        System.out.println("Password changed successfully.");

    }

    //password validity check
    public boolean passwordCheck(String password){
        if (!(password!= null && password.length() > 8 ))
            {
             return false;   
            }
            
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        return hasUpper && hasLower && hasNumber ; 
    }
    

    public User(String firstName, String lastName, String password){

        if (!passwordCheck(password)) {
            throw new IllegalArgumentException("Invalid password : Must contain at least one uppercase letter, lowercase letter and a number");
        } 
        this.employeeId = nextEmployeeId++;
        
        setFirstName(firstName);
        setLastName(lastName);

        this.userName = getLastName().toLowerCase()+getEmployeeId();
        setPassword(password);
     }
     public String toString(){
        String tag = "\nName : "+getFirstName()+" "+getLastName();
        tag += "\nEmployee ID : "+getEmployeeId();
        tag += "\nUserName : "+getUserName();
        tag += "\nPassword : "+getPassword();
        return tag;


     }
}

