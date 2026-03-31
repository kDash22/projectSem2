package salessystem.gui.customergui;

import salessystem.dao.CustomerDAO;
import salessystem.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GetAllCustomersGUI extends JDialog {


    public GetAllCustomersGUI(JFrame parent ){

        super(parent, "All Customers", true);


        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        customerTable(panel);

    }

    public static void customerTable(JPanel panel){


        String[] columns = {" Customer ID ", " Customer Name ", " Contact Number "};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,350,250);
        panel.add(scrollPane);

        CustomerDAO cdao = new CustomerDAO();
        List<Customer> customers = cdao.getAllCustomers();

        model.setRowCount(0);

        for (Customer customer : customers){
            Object[] row = {
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getContactNumber()
            };
            model.addRow(row);

        }

    }


}
