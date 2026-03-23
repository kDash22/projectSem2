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
    
    public final void setFirstName(String firstName) throws IllegalArgumentException{
        if(firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName;
    }
    
    public final void setLastName(String lastName) throws IllegalArgumentException{
        if(lastName == null || lastName.trim().isEmpty()){
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName;
    }
    public final void setPassword(String password) throws IllegalArgumentException{
        if (!passwordCheck(password)) {
            throw new IllegalArgumentException("Invalid password : Must contain at least one uppercase letter, lowercase letter and a number");
        } 
        this.password = password;
    }

    public final void changePassword(String currentPassword, String newPassword) { 
        if (!currentPassword.equals(getPassword())) {
            System.out.println("Incorrect current password!!!");
            
            
    }
        else{
        setPassword(newPassword);
        System.out.println("Password changed successfully.");}

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

