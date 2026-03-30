package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;

public class AddCustomerGUI extends JDialog {

    private final JTextField nameText;
    private final JTextField contactNoText;
    private final JLabel contactNoStatus;
    private final JLabel nameStatus;

    public AddCustomerGUI(JFrame parent){
        super(parent, "Add New User", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel firstName = new JLabel("Customer Name ");
        firstName.setBounds(10, 20, 120, 30);
        panel.add(firstName);

        nameText = new JTextField(25);
        nameText.setBounds(140, 20, 165, 25);
        panel.add(nameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 50, 500, 30);
        nameStatus.setForeground(Color.red);
        panel.add(nameStatus);

        JLabel contactNo = new JLabel("Contact Number ");
        contactNo.setBounds(10, 80, 120, 30);
        panel.add(contactNo);

        contactNoText = new JTextField(25);
        contactNoText.setBounds(140, 80, 165, 25);
        panel.add(contactNoText);

        contactNoStatus = new JLabel("");
        contactNoStatus.setBounds(10, 110, 500, 30);
        contactNoStatus.setForeground(Color.red);
        panel.add(contactNoStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 150, 165, 25);
        button.addActionListener(e -> newCustomer());
        panel.add(button);


    }
    public void newCustomer(){
        String name = nameText.getText();
        String contactNo = contactNoText.getText();
        boolean nameCheck = false;
        boolean contactNoCheck = false;


        if (name !=null && !name.isBlank()){
            nameCheck = true;
            nameStatus.setText("");
        } else {
            nameStatus.setText(" Customer name cannot be empty ! ");
        }
        if (Customer.validateContactNumber(contactNo)){
            contactNoCheck = true;
        } else {
            contactNoStatus.setText(" Please enter a valid contact number ! ");
        }
        if (contactNoCheck && nameCheck){
            Customer customer = new Customer(name, contactNo);
            CustomerDAO cdao = new CustomerDAO();
            cdao.addCustomer(customer);
            JOptionPane.showMessageDialog(this, " New Customer Created Successfully ! ");
            dispose();

        }else {
            JOptionPane.showMessageDialog(this, " Error ! ");
            contactNoText.setText("");
            nameText.setText("");

        }

    }
}
