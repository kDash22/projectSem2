package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for updating customer contact number
public class UpdateCustomerContactNoGUI extends JDialog{

    private final JTextField customerIDText; //input field for customer id
    private final JLabel customerIDStatus;//displays errors regarding customer id
    private final JTextField contactNoText;//input field for customer number
    private final JLabel contactNoStatus;//displays errors regarding the contact number

    //initialise the JDialog
    //includes
    // - customer info form (left)
    // - customer table (right)
    // - confirm button

    public UpdateCustomerContactNoGUI(JFrame parent){
        super(parent, "Update The Contact Number", true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //panel that contains the table
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

        JLabel ContactNo = new JLabel("Contact Number ");
        ContactNo.setBounds(10, 80, 120, 30);
        parentPanel.add(ContactNo);

        contactNoText = new JTextField(25);
        contactNoText.setBounds(120, 80, 165, 25);
        parentPanel.add(contactNoText);

        contactNoStatus = new JLabel("");
        contactNoStatus.setBounds(10, 110, 500, 30);
        contactNoStatus.setForeground(Color.red);
        parentPanel.add(contactNoStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);

        //handles the contact number update and UI refresh after
        button.addActionListener(e -> {
            updateCustomerContactNo();

            //table refresh after update
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GetAllCustomersGUI.customerTable(panel);

        });

        parentPanel.add(button);



    }

    //validates the info
    //updates the database using the DAO
    public void updateCustomerContactNo(){
        String customerID = customerIDText.getText();
        String contactNo = contactNoText.getText();
        Customer customer = null;
        CustomerDAO cdao = new CustomerDAO();

        //customer id cannot be empty or blank
        if(customerID !=null && !customerID.isBlank()){

            contactNoStatus.setText("");
            customerIDStatus.setText("");

            int cusid = Integer.parseInt(customerID);
            customer = cdao.getCustomerByCustomerID(cusid);

            //validates the contact number using a method from the customer class
            if (Customer.validateContactNumber(contactNo)){
                contactNoStatus.setText("");
                customerIDStatus.setText("");

                //if customer exists
                if (customer != null){
                    customer.setContactNumber(contactNo);
                    cdao.updateCustomerContact(cusid, customer);
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
