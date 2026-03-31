package salessystem.gui.salesgui;

import salessystem.Global;
import salessystem.dao.SaleDAO;
import salessystem.model.Sale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

//provides the JDialog
public class GetAllSalesGUI extends JDialog {

    //initialise the JDialog
    //incluedes the sales table
    public GetAllSalesGUI(JFrame parent ){

        super(parent, "All Sales", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        salesTable(panel);

    }

    //method that makes the table
    public static void salesTable(JPanel panel){
        String[] columns = {" Sale ID ", " Customer ID ", "Customer Name", " Date ", "total"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,550,250);
        panel.add(scrollPane);

        SaleDAO sdao = new SaleDAO();
        List<Sale> sales = sdao.getAllSales();

        model.setRowCount(0);
        if (!sales.isEmpty()){

            for (Sale sale : sales){
                int customerID = -1;
                String customerName = " Deleted Customer ";
                if (!(sale.getCustomer() == null)){
                    customerID = sale.getCustomer().getCustomerID();
                    customerName = sale.getCustomer().getName();
                }
                Object[] row = {
                        sale.getSaleID(),
                        customerID,
                        customerName,
                        Global.dateTimeFormat(sale.getDateTime()),
                        "Rs"+" "+sale.getTotal()
                };
                model.addRow(row);
            }

        }else {
            JOptionPane.showMessageDialog(null, " Sales table is empty ! ");
        }

    }

}
