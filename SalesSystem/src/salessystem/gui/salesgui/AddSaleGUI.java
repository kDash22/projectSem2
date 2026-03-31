package salessystem.gui.salesgui;

import salessystem.dao.CustomerDAO;
import salessystem.dao.ProductDAO;
import salessystem.dao.SaleDAO;
import salessystem.model.Customer;
import salessystem.model.Product;
import salessystem.model.Sale;
import salessystem.model.SaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//provides JDialog for inserting sales
public class AddSaleGUI extends JDialog {

    private final JTextField productIDField;//input field for product id
    private final JTextField quantityField;//input field for quantity
    private final DefaultTableModel model;//data holder
    private final JTable table;//table
    private final JLabel status = new JLabel("");//displays errors
    private JTextField customerIDField;//input field for customer id
    private List<SaleItem> saleItems = new ArrayList<>();//sale items list
    private JLabel cusid;//used to display the valid customer id


    //initialise the JDialog
    //includes
    // - sale info form
    // - table for sale items added
    // - add item and save sale button
    public AddSaleGUI(JFrame parent) {
        super(parent, " Add Sale ", true);

        setSize(700, 400);
        setLocationRelativeTo(parent);
        setLayout(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel customerLabel = new JLabel("Customer ID ");
        customerLabel.setBounds(20, 20, 150, 25);
        add(customerLabel);

        customerIDField = new JTextField();
        customerIDField.setBounds(130, 20, 150, 25);
        add(customerIDField);

        cusid = new JLabel("");
        cusid.setBounds(130, 20, 150, 25);
        add(cusid);

        JLabel prodLabel = new JLabel("Product ID ");
        prodLabel.setBounds(20, 60, 100, 25);
        add(prodLabel);

        productIDField = new JTextField();
        productIDField.setBounds(130, 60, 150, 25);
        add(productIDField);

        JLabel quantityLabel = new JLabel("Quantity ");
        quantityLabel.setBounds(20, 100, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(130, 100, 150, 25);
        add(quantityField);

        JButton addItemBtn = new JButton("Add Item");
        addItemBtn.setBounds(300, 60, 130, 30);
        add(addItemBtn);

        model = new DefaultTableModel(new String[]{"Product ID", " Unit Type ","Quantity"}, 0);
        table = new JTable(model);

        //adds scroll functionality
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 150, 650, 160);
        add(pane);

        JButton saveBtn = new JButton("Save Sale");
        saveBtn.setBounds(260, 330, 150, 30);
        add(saveBtn);


        status.setBounds(20, 330, 220, 30);
        status.setForeground(Color.red);
        add(status);

        //handles adding sale items to the sale
        addItemBtn.addActionListener(e -> addItem());

        //handles saving sales
        saveBtn.addActionListener(e -> saveSale(parent));

    }

    //validate given info
    //adds sales item to the table
    //changes product stock quantity in the databse using the DAO
    public void addItem() {
        try {
            int customerId = Integer.parseInt(customerIDField.getText().trim());

            CustomerDAO cdao = new CustomerDAO();
            Customer customer = cdao.getCustomerByCustomerID(customerId);

            if (customer == null) {
                status.setText("Customer not found");
                return;
            }
            customerIDField.setVisible(false);
            cusid.setText(" "+String.valueOf(customerId));

            int productID = Integer.parseInt(productIDField.getText().trim());
            double quantity = Double.parseDouble(quantityField.getText().trim());

            ProductDAO pdao = new ProductDAO();
            Product product = pdao.getProductByProductID(productID);

            if (product == null) {
                status.setText("Product not found");

            } else {
                status.setText("");
                if (quantity <= 0) {
                    status.setText("Invalid quantity");
                    return;
                }
                    if (product.getStock() >= quantity) {
                        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // if user adds a sale item, a sale must be saved
                        status.setText("");
                        SaleItem item = new SaleItem(product, quantity);
                        saleItems.add(item);

                        model.addRow(new Object[]{
                                productID,
                                product.getUnitType().name(),
                                quantity
                        });

                        product.setStock(product.getStock() - quantity);
                        pdao.updateProductStock(productID, product);

                        JOptionPane.showMessageDialog(this, "Sale Item saved successfully.");

                    } else {
                        status.setText(" The quality cannot exceed the available stock ! ");

                    }



            }


        } catch (Exception e) {
            status.setText("Invalid input");
        }
    }

    //saves the sale to the database using the DAO
    public void saveSale(JFrame parent) {

        if (saleItems.isEmpty()) {
            status.setText("Add at least 1 item");
            return;
        }

        try {
            int customerId = Integer.parseInt(customerIDField.getText().trim());

            CustomerDAO cdao = new CustomerDAO();
            Customer customer = cdao.getCustomerByCustomerID(customerId);

            if (customer == null) {
                status.setText("Customer not found");
                return;
            }

            Sale sale = new Sale(customer);
            for (SaleItem item : saleItems){
                sale.addSaleItem(item);
            }

            SaleDAO sdao = new SaleDAO();
            sdao.addSale(sale);

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Generate invoice?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                PrintInvoiceGUI gui =new PrintInvoiceGUI(parent, sale.getSaleID());
                gui.setVisible(true);
                dispose();
            } else {
                dispose();
            }

        }catch (Exception e){
            status.setText("Error at the last catch saving sale !");
        }
    }
}