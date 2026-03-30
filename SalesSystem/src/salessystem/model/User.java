package salessystem.model;

public abstract class User {
    protected String firstName;
    protected String lastName;
    private int userID;
    private String userName;
    private String password;

    abstract void displayRole();

    //getters

    public int getUserID() {
        return userID;
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
    public abstract String getRole();

    //setters
    public final void setUserID(int userID){
        if (this.userID != 0) { // it gets set by the database as userID is the primary key and is set to auto increment
            throw new IllegalStateException("ID already set");
        }
        this.userID = userID;
    }


    public final void setFirstName(String firstName) throws IllegalArgumentException{
        validateFirstName(firstName);
        this.firstName = firstName;
    }
    
    public final void setLastName(String lastName) throws IllegalArgumentException{
        validateLastName(lastName);
        this.lastName = lastName;
    }
    public final void setPassword(String password) throws IllegalArgumentException{
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Invalid password : Must contain at least one uppercase letter, lowercase letter and a number");
        } 
        this.password = password;
    }
    public final void createUserName(){
        this.userName = getLastName().trim().toLowerCase() + getUserID();
    }

    public final void changePassword(String currentPassword, String newPassword) { 
        if (!currentPassword.equals(getPassword())) {
            System.out.println("Incorrect current password!!!");
        }
        else{
            setPassword(newPassword);
            System.out.println("Password changed successfully.");}

    }
    //for new users, the database will set the userID
    public User(String firstName, String lastName, String password){

        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);

    }

    //for retrieving old users from the database
    public User(int userID,String firstName, String lastName, String userName, String password){
        this(firstName,lastName,password);
        this.userID = userID;
        this.userName = userName;
    }

    @Override
    public String toString(){
        String tag = "\nName : "+getFirstName()+" "+getLastName();
        tag += "\nUser ID : "+ getUserID();
        tag += "\nUserName : "+getUserName();
        tag += "\nPassword : "+getPassword();
        return tag;
    }

     //validation
     public void validateFirstName(String firstName){
         if(firstName == null || firstName.trim().isEmpty()){
             throw new IllegalArgumentException("First name cannot be empty");
         }
     }

     public void validateLastName(String lastName){
         if(lastName == null || lastName.trim().isEmpty()){
             throw new IllegalArgumentException("Last name cannot be empty");
         }
     }

    public static boolean validatePassword(String password){
        if (!(password!= null && password.length() > 8 ))
        {
            return false;
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        return hasUpper && hasLower && hasNumber ;
    }



}

