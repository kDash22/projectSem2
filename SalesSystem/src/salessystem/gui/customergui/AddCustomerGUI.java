package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.gui.usergui.GetAllUserGUI;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for adding customers
public class AddCustomerGUI extends JDialog {

    private final JTextField nameText; //input field for customer name
    private final JTextField contactNoText; //input field for contact number
    private final JLabel contactNoStatus;//displays errors regarding contact number
    private final JLabel nameStatus;//displays errors regarding the name

    //initialises the JDialog
    //includes
    // - the customer info form (left)
    // - the customer table (right)
    // - a confirm button
    public AddCustomerGUI(JFrame parent){

        super(parent, "Add New Customer", true);

        setSize(1000, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main container
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //panel displaying the customer table
        JPanel panel = new JPanel();
        panel.setBounds(300,20,700,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllCustomersGUI.customerTable(panel);

        JLabel firstName = new JLabel("Customer Name ");
        firstName.setBounds(10, 20, 120, 30);
        parentPanel.add(firstName);

        nameText = new JTextField(25);
        nameText.setBounds(140, 20, 165, 25);
        parentPanel.add(nameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 50, 500, 30);
        nameStatus.setForeground(Color.red);
        parentPanel.add(nameStatus);

        JLabel contactNo = new JLabel("Contact Number ");
        contactNo.setBounds(10, 80, 120, 30);
        parentPanel.add(contactNo);

        contactNoText = new JTextField(25);
        contactNoText.setBounds(140, 80, 165, 25);
        parentPanel.add(contactNoText);

        contactNoStatus = new JLabel("");
        contactNoStatus.setBounds(10, 110, 500, 30);
        contactNoStatus.setForeground(Color.red);
        parentPanel.add(contactNoStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 150, 165, 25);

        //handles the customer creation and UI refresh
        button.addActionListener(e -> {
            newCustomer();

            //refresh the table after creation
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllCustomersGUI.customerTable(panel);

        });
        parentPanel.add(button);


    }

    //validates entered info
    //persist info to the database using the DAO
    public void newCustomer(){
        String name = nameText.getText();
        String contactNo = contactNoText.getText();
        boolean nameCheck = false;
        boolean contactNoCheck = false;

        //validate name (cannot be empty or blank)
        if (name !=null && !name.isBlank()){
            nameCheck = true;
            nameStatus.setText("");
        } else {
            nameStatus.setText(" Customer name cannot be empty ! ");
        }

        //validate contact number using a method from customer class
        if (Customer.validateContactNumber(contactNo)){
            contactNoCheck = true;
        } else {
            contactNoStatus.setText(" Please enter a valid contact number ! ");
        }

        //persist if only both validations pass
        if (contactNoCheck && nameCheck){
            Customer customer = new Customer(name, contactNo);
            CustomerDAO cdao = new CustomerDAO();
            cdao.addCustomer(customer);
            JOptionPane.showMessageDialog(this, " New Customer Created Successfully ! ");
            contactNoText.setText("");
            nameText.setText("");

        }else {
            JOptionPane.showMessageDialog(this, " Error ! ");
            contactNoText.setText("");
            nameText.setText("");
        }

    }
}
