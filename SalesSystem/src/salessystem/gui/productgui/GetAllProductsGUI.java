package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GetAllProductsGUI extends JDialog {
    private JTable table;
    private DefaultTableModel model;

    public GetAllProductsGUI(JFrame parent ){

        super(parent, "All Products", true);


        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        String[] columns = {" Product ID ", " Product Name ", " Unit type ", " Price ", " Stock "};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

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
                    product.getPrice(),
                    product.getStock()
            };
            model.addRow(row);
        }

    }
}
