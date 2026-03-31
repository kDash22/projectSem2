package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchCustomersGUI extends JDialog {

    private final JTextField textfield;
    private JLabel msg = new JLabel("");
    private JLabel customerID = new JLabel("");
    private JLabel name = new JLabel("");
    private JLabel contactNumber = new JLabel("");

    public SearchCustomersGUI(JFrame parent){

        super(parent, " Search Customers ",true);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

    public void searchCustomer(JPanel panel){

        String cusID = textfield.getText();
        Customer customer = null;
        CustomerDAO customerDAO = new CustomerDAO();

        if (cusID != null && !cusID.isBlank()){
            int cusid = Integer.parseInt(cusID);
            customer = customerDAO.getCustomerByCustomerID(cusid);}

        if( customer != null){

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
