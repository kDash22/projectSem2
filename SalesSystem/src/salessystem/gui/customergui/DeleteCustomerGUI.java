package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteCustomerGUI extends JDialog {

    private final JTextField textField;
    private final JLabel customerIDStatus;

    public DeleteCustomerGUI(JFrame parent){
        super(parent, "Delete Customer",true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

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
        button.addActionListener(e -> {
            deleteCustomer();

            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GetAllCustomersGUI.customerTable(panel);
        });
        parentPanel.add(button);


    }
    public void deleteCustomer(){
        String customerID = textField.getText();

        if (customerID != null && !customerID.isBlank()) {

            int cusid = Integer.parseInt(customerID);
            customerIDStatus.setText("");

            CustomerDAO cdao = new CustomerDAO();
            Customer customer = cdao.getCustomerByCustomerID(cusid);

            if (customer != null){
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
