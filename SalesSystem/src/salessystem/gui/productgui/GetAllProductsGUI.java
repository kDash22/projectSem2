package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

//provides the JDialog for getting all products
public class GetAllProductsGUI extends JDialog {

    //initialise the JDialog
    //includes
    // - product table
    public GetAllProductsGUI(JFrame parent ){

        super(parent, "All Products", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        productsTable(panel);



    }

    //method that make the table
    public static void productsTable(JPanel panel){
        String[] columns = {" Product ID ", " Product Name ", " Unit type ", " Price ", " Stock "};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,550,250);
        panel.add(scrollPane);

        ProductDAO pdao = new ProductDAO();
        List<Product> products = pdao.getAllProducts();

        model.setRowCount(0);

        for (Product product : products){
            Object[] row = {
                    product.getProductId(),
                    product.getProductName(),
                    product.getUnitType().name(),
                    "Rs."+" "+product.getPrice(),
                    product.getStock()
            };
            model.addRow(row);
        }
    }
}
