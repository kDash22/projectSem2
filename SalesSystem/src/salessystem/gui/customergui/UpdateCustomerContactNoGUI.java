package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomerContactNoGUI extends JDialog{

    private final JTextField customerIDText;
    private final JLabel customerIDStatus;
    private final JTextField contactNoText;
    private final JLabel contactNoStatus;

    public UpdateCustomerContactNoGUI(JFrame parent){
        super(parent, "Update The Contact Number", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel customerID = new JLabel("Customer ID ");
        customerID.setBounds(10, 20, 120, 30);
        panel.add(customerID);

        customerIDText= new JTextField(25);
        customerIDText.setBounds(120, 20, 165, 25);
        panel.add(customerIDText);

        customerIDStatus = new JLabel("");
        customerIDStatus.setBounds(10, 50, 500, 30);
        customerIDStatus.setForeground(Color.red);
        panel.add(customerIDStatus);

        JLabel ContactNo = new JLabel("Contact Number ");
        ContactNo.setBounds(10, 80, 120, 30);
        panel.add(ContactNo);

        contactNoText = new JTextField(25);
        contactNoText.setBounds(120, 80, 165, 25);
        panel.add(contactNoText);

        contactNoStatus = new JLabel("");
        contactNoStatus.setBounds(10, 110, 500, 30);
        contactNoStatus.setForeground(Color.red);
        panel.add(contactNoStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);
        button.addActionListener(e -> updateCustomerContactNo());
        panel.add(button);



    }
    public void updateCustomerContactNo(){
        String customerID = customerIDText.getText();
        String contactNo = contactNoText.getText();
        Customer customer = null;
        CustomerDAO cdao = new CustomerDAO();

        if(customerID !=null && !customerID.isBlank()){

            contactNoStatus.setText("");
            customerIDStatus.setText("");

            int cusid = Integer.parseInt(customerID);
            customer = cdao.getCustomerByCustomerID(cusid);

            if (Customer.validateContactNumber(contactNo)){
                contactNoStatus.setText("");
                customerIDStatus.setText("");

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
