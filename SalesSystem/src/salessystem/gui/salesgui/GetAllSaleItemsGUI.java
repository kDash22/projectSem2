package salessystem.gui.salesgui;

import salessystem.dao.SaleItemDAO;
import salessystem.model.SaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GetAllSaleItemsGUI extends JDialog{

    private JTable table;
    private DefaultTableModel model;

    public GetAllSaleItemsGUI(JFrame parent) {

        super(parent, "All Sale Items", true);


        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        String[] columns = {" Sale Item ID ", " Product ID "," Product Name "," Unit Type "," Quantity "  ,  "Subtotal"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 550, 250);
        panel.add(scrollPane);

        SaleItemDAO sidao = new SaleItemDAO();
        List<SaleItem> saleitems = sidao.getAllSaleItems();

        model.setRowCount(0);

        for (SaleItem saleItem : saleitems) {
            Object[] row = {
                    saleItem.getSaleItemID(),
                    saleItem.getProduct().getProductId(),
                    saleItem.getProduct().getProductName(),
                    saleItem.getProduct().getUnitType(),
                    saleItem.getQuantity(),
                    "Rs"+" "+saleItem.getSubtotal()
            };
            model.addRow(row);
        }
    }
}