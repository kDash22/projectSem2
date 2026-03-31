package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for updating the customer
public class UpdateCustomerGUi extends JDialog {

    private final JTextField customerIDText;//customer id input field
    private final JLabel customerIDStatus;//displays errors regarding customer id
    private final JTextField nameText;// input field for customer name
    private final JLabel nameStatus;// displays errors regarding the name
    private final JTextField contactNoText;// input field for the contact number
    private final JLabel contactNoStatus;//displays errors regarding the contact number

    //initialise the JDialog
    //includes
    // - customer info form (left)
    // - customer table (right)
    // -  confirm button
    public UpdateCustomerGUi(JFrame parent){

        super(parent, "Update Customer ", true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //table panel
        JPanel panel = new JPanel();
        panel.setBounds(300,20,700,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllCustomersGUI.customerTable(panel);



        JLabel customerID = new JLabel("Customer ID ");
        customerID.setBounds(10, 20, 120, 30);
        parentPanel.add(customerID);

        customerIDText= new JTextField(25);
        customerIDText.setBounds(120, 20, 165, 25);
        parentPanel.add(customerIDText);

        customerIDStatus = new JLabel("");
        customerIDStatus.setBounds(10, 50, 500, 30);
        customerIDStatus.setForeground(Color.red);
        parentPanel.add(customerIDStatus);

        JLabel name = new JLabel("Name ");
        name.setBounds(10, 80, 120, 30);
        parentPanel.add(name);

        nameText = new JTextField(25);
        nameText.setBounds(120, 80, 165, 25);
        parentPanel.add(nameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 110, 500, 30);
        nameStatus.setForeground(Color.red);
        parentPanel.add(nameStatus);

        JLabel ContactNo = new JLabel("Contact Number ");
        ContactNo.setBounds(10, 140, 120, 30);
        parentPanel.add(ContactNo);

        contactNoText = new JTextField(25);
        contactNoText.setBounds(120, 140, 165, 25);
        parentPanel.add(contactNoText);

        contactNoStatus = new JLabel("");
        contactNoStatus.setBounds(10, 170, 500, 30);
        contactNoStatus.setForeground(Color.red);
        parentPanel.add(contactNoStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 200, 165, 25);

        //handles the updating and UI refresh
        button.addActionListener(e -> {
            updateCustomer();

            //table refresh
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllCustomersGUI.customerTable(panel);
        });
        parentPanel.add(button);

    }

    //validate info
    //updates the database using the DAO
    public void updateCustomer(){
        String customerID = customerIDText.getText();
        String name = nameText.getText();
        String contactNo = contactNoText.getText();
        Customer customer = null;
        CustomerDAO cdao = new CustomerDAO();

        //name cannot be empty or blank
        if (name != null && !name.isBlank()){
            nameStatus.setText("");
        }else {
            nameStatus.setText(" Name cannot be empty ! ");
        }
        //contact number cannot be empty or blank
        if (contactNo != null && !contactNo.isBlank()){
            contactNoStatus.setText("");
        }else {
            contactNoStatus.setText(" Contact number cannot be empty ! ");
        }

        //if customer id is not blank or not empty
        if(customerID !=null && !customerID.isBlank()){
            contactNoStatus.setText("");
            customerIDStatus.setText("");

            int cusid = Integer.parseInt(customerID);
            customer = cdao.getCustomerByCustomerID(cusid);

            //validates contact number using a method from customer class
            if (Customer.validateContactNumber(contactNo)){
                contactNoStatus.setText("");
                customerIDStatus.setText("");

                //customer cannot be null and name cannot be empty or blank
                if (customer != null && name != null && !name.isBlank()){
                    customer.setContactNumber(contactNo);
                    customer.setName(name);
                    cdao.updateCustomer(cusid, customer);
                    JOptionPane.showMessageDialog(this, " Contact Number Updated Successfully ! ");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, " Customer not registered  ! ");

                }

            } else {
                contactNoStatus.setText(" Please enter a valid Contact Number ! ");
            }
        }else {
            customerIDStatus.setText(" Customer ID cannot be empty ! ");
        }


    }
}
