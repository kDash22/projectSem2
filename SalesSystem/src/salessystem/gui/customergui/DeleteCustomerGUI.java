package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

//JDialog for deleting a customer
public class DeleteCustomerGUI extends JDialog {

    private final JTextField textField;//input field for customer id
    private final JLabel customerIDStatus;//displays errors regarding customer id

    //initialise the JDialog
    //contains
    // - customer id field (right)
    // - customer table (left)
    // - confirm button
    public DeleteCustomerGUI(JFrame parent){
        super(parent, "Delete Customer",true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //panel containing the table
        JPanel panel = new JPanel();
        panel.setBounds(300,20,700,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllCustomersGUI.customerTable(panel);

        JLabel customerId = new JLabel("Customer ID ");
        customerId.setBounds(10, 20, 80, 30);
        parentPanel.add(customerId);

        textField = new JTextField(25);
        textField.setBounds(135, 20, 165, 25);
        parentPanel.add(textField);

        customerIDStatus = new JLabel("");
        customerIDStatus.setBounds(10, 50, 200, 30);
        customerIDStatus.setForeground(Color.red);
        parentPanel.add(customerIDStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);

        //handles customer deletion and UI refresh
        button.addActionListener(e -> {

            deleteCustomer();

            //refresh table after deletion
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GetAllCustomersGUI.customerTable(panel);
        });
        parentPanel.add(button);


    }

    //validates the entered info
    //updates the datbase using the DAO
    public void deleteCustomer(){

        String customerID = textField.getText();

        if (customerID != null && !customerID.isBlank()) {

            int cusid = Integer.parseInt(customerID);
            customerIDStatus.setText("");

            CustomerDAO cdao = new CustomerDAO();
            Customer customer = cdao.getCustomerByCustomerID(cusid);

            //customer cannot be null, they must exist
            if (customer != null){

                //confirm deletion
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    cdao.deleteCustomer(cusid);
                    JOptionPane.showMessageDialog(this, " Customer deleted successfully.");

                }
                else if (result == JOptionPane.NO_OPTION) {

                }

            }else {
                JOptionPane.showMessageDialog(this, " Customer not found ! ");
                textField.setText("");

            }


        } else {
            customerIDStatus.setText(" Customer ID cannot be blank ! ");
        }
    }
}
