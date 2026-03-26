package salessystem;

public class Customer {
    private String nic ;
    private String name;
    private String contactNumber;

    //setters
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setNic(String nic) {
        validateNic(nic);
        this.nic = nic;
    }

    public void setContactNumber(String contactNumber) {
        validateContactNumber(contactNumber);
        this.contactNumber = contactNumber;
    }

    //getters

    public String getContactNumber() {
        return contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public String getName() {
        return name;
    }

    public Customer(String nic, String name, String contactNumber){

        setNic(nic);
        setName(name);
        setContactNumber(contactNumber);

    }

    @Override
    public String toString(){
        String msg = "\nName : "+getName();
        msg += "\nNIC Number : "+getNic();
        msg += "\nContact Number : "+getContactNumber();
        return msg;
    }
    public static boolean isNumericOnly(String value) {
        return value != null && value.matches("\\d+");
    }

    //validation
    public void validateName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(" Customer name cannot be empty ! ");
        }
    }

    public void validateNic(String nic){
        if(nic == null || nic.trim().isEmpty()){
            throw new IllegalArgumentException(" NIC number cannot be empty ! ");
        }
        if (!isNumericOnly(nic)){
            throw new IllegalArgumentException(" NIC only contains numbers ! ");
        }
        if (nic.length() != 12) {
            throw new IllegalArgumentException(" An NIC number must contain 12 digits ! ");
        }

    }

    public void validateContactNumber(String contactNumber){
        if (contactNumber == null || contactNumber.trim().isEmpty()){
            throw new IllegalArgumentException(" The contact number cannot be empty ! ");
        }
        if (contactNumber.length() != 10) {
            throw new IllegalArgumentException(" The contact number must contain 10 digits ! ");
        }
        if (!isNumericOnly(contactNumber)){
            throw new IllegalArgumentException(" The contact number only contains numbers ! ");
        }

    }











}
