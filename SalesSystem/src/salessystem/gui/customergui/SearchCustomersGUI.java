package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

//JDialog for searching customers
public class SearchCustomersGUI extends JDialog {

    private final JTextField textfield; //input field for customer id
    private JLabel msg = new JLabel(""); //displays error msgs
    private JLabel customerID = new JLabel("");//displays errors regarding the customer id
    private JLabel name = new JLabel("");//displays the customer name
    private JLabel contactNumber = new JLabel("");//displays the customer contact number

    //initialise the JDialog
    //includes
    // - customer id input field
    // - search button
    public SearchCustomersGUI(JFrame parent){

        super(parent, " Search Customers ",true);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel userLabel = new JLabel("Customer ID ");
        userLabel.setBounds(10,20,80,30);
        panel.add(userLabel);

        textfield = new JTextField(25);
        textfield.setBounds(100,20,165,25);
        panel.add(textfield);

        JButton button = new JButton("Search");
        button.setBounds(10,50,80,25);
        button.addActionListener(e-> searchCustomer(panel));
        panel.add(button);


    }

    //validates the customer id
    //retrieves the data from the database using the DAO
    public void searchCustomer(JPanel panel){

        String cusID = textfield.getText();
        Customer customer = null;
        CustomerDAO customerDAO = new CustomerDAO();

        //customer id cannot be emopty or blank
        if (cusID != null && !cusID.isBlank()){
            int cusid = Integer.parseInt(cusID);
            customer = customerDAO.getCustomerByCustomerID(cusid);}

        //if customer exists
        if( customer != null){

            //clears all the labels
            msg.setText("");
            customerID.setText("");
            name.setText("");
            contactNumber.setText("");


            msg.setText("Customer Found !");
            msg.setForeground(Color.black);
            msg.setBounds(10,100,400,25);

            customerID.setText("Customer ID: "+ customer.getCustomerID());
            customerID.setBounds(10,130,400,25);

            name.setText("Name : "+customer.getName());
            name.setBounds(10,160,400,25);

            contactNumber.setText("Contact Number : "+customer.getContactNumber());
            contactNumber.setBounds(10,190,400,25);

            panel.add(msg);
            panel.add(customerID);
            panel.add(name);
            panel.add(contactNumber);


        }else {
            msg.setText("");
            customerID.setText("");
            name.setText("");
            contactNumber.setText("");

            msg.setText("Customer Not found !");
            msg.setBounds(10,100,400,25);
            msg.setForeground(Color.red);
            panel.add(msg);

        }
        panel.revalidate();
        panel.repaint();

    }

}
