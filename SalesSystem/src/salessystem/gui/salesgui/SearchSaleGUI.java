package salessystem.gui.salesgui;

import salessystem.Global;
import salessystem.dao.SaleDAO;
import salessystem.model.Sale;
import salessystem.model.SaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchSaleGUI extends JDialog {

    private JTable table;//table
    private DefaultTableModel model;//data holder
    private JScrollPane scrollPane; // adds the scroll function

    private JLabel msg = new JLabel(""); //display msgs
    private JLabel customerID = new JLabel(""); // display the customer id
    private JLabel total = new JLabel(""); // display the total
    private JLabel date = new JLabel("");// display the date
    private JTextField textfield; //input field for sale id

    //initialise the JDialog
    // includes
    // - sale id field
    // - search button
    public SearchSaleGUI(JFrame parent){
        super(parent, " Search Sales ",true);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel userLabel = new JLabel("Sale ID ");
        userLabel.setBounds(10,20,80,30);
        panel.add(userLabel);

        textfield = new JTextField(25);
        textfield.setBounds(100,20,165,25);
        panel.add(textfield);

        JButton button = new JButton("Search");
        button.setBounds(10,50,80,25);

        //handles the search and table update
        button.addActionListener(e-> searchSale(panel));
        panel.add(button);


    }

    //validates the entered data
    //retrieves the data from the database
    //displays the sale item data in a table

    public void searchSale(JPanel panel){
        String saleIdString = textfield.getText();
        Sale sale = null;
        SaleDAO sdao = new SaleDAO();

        //sale id cannot be empty or blank
        if (saleIdString != null && !saleIdString.isBlank()){
            int saleId = Integer.parseInt(saleIdString);
            sale = sdao.getSaleBySaleID(saleId);

            //refreshes the table if it is filled
            if (scrollPane != null) {
                panel.remove(scrollPane);
                scrollPane = null;
                table = null;
                model = null;

                panel.revalidate();
                panel.repaint();
            }

            //if sale exists
            if(sale != null){
                msg.setText("");
                customerID.setText("");
                total.setText("");
                date.setText("");

                msg.setText("Sale Found !");
                msg.setForeground(Color.black);
                msg.setBounds(10,100,400,25);

                //if customer exists
                if (!(sale.getCustomer() == null)){
                    customerID.setText("Customer ID: "+ sale.getCustomer().getCustomerID());
                    customerID.setBounds(10,130,400,25);
                }else {
                    customerID.setText("Customer ID: Deleted Customer ");
                    customerID.setBounds(10,130,400,25);
                }

                total.setText("Total : "+sale.getTotal());
                total.setBounds(10,160,400,25);

                date.setText("Date : "+ Global.dateTimeFormat(sale.getDateTime()));
                date.setBounds(10,190,400,25);

                panel.add(msg);
                panel.add(customerID);
                panel.add(total);
                panel.add(date);

                String[] columns = {" Sale Item ID", " product ID ", " Quantity ", "Subtotal"};
                model = new DefaultTableModel(columns, 0);

                table = new JTable(model);

                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(10,220,550,250);
                panel.add(scrollPane);


                List<SaleItem> items = sale.getSaleItems();

                model.setRowCount(0);

                for (SaleItem item : items){
                    Object[] row = {
                            item.getSaleItemID(),
                            item.getProduct().getProductId(),
                            item.getQuantity(),
                            "Rs"+" "+item.getSubtotal()
                    };
                    model.addRow(row);
                }


            }else {
                msg.setText("");
                customerID.setText("");
                total.setText("");
                date.setText("");

                msg.setText(" Sale not found ! ");
                msg.setBounds(10,100,400,25);
                msg.setForeground(Color.red);
                panel.add(msg);

            }
            panel.revalidate();
            panel.repaint();
        }

    }


}
