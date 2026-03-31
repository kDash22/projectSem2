package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;
import salessystem.model.UnitType;

import javax.swing.*;
import java.awt.*;

public class UpdateProductStockGUI extends JDialog {

    private final JTextField productIDText;
    private final JLabel productIDStatus;
    private final JTextField stockText;
    private final JLabel stockStatus;

    public UpdateProductStockGUI(JFrame parent) {
        super(parent, "Update Product Stock", true);

        setSize(1000, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        JPanel panel = new JPanel();
        panel.setBounds(300,20,700,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllProductsGUI.productsTable(panel);

        JLabel productId = new JLabel("Product ID ");
        productId.setBounds(10, 20, 120, 30);
        parentPanel.add(productId);

        productIDText = new JTextField(25);
        productIDText.setBounds(120, 20, 165, 25);
        parentPanel.add(productIDText);

        productIDStatus = new JLabel("");
        productIDStatus.setBounds(10, 50, 500, 30);
        productIDStatus.setForeground(Color.red);
        parentPanel.add(productIDStatus);

        JLabel Stock = new JLabel("Stock ");
        Stock.setBounds(10, 80, 120, 30);
        parentPanel.add(Stock);

        stockText = new JTextField(25);
        stockText.setBounds(120, 80, 165, 25);
        parentPanel.add(stockText);

        stockStatus = new JLabel("");
        stockStatus.setBounds(10, 110, 500, 30);
        stockStatus.setForeground(Color.red);
        parentPanel.add(stockStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);
        button.addActionListener(e -> {
            updateStock();
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllProductsGUI.productsTable(panel);
        });
        parentPanel.add(button);


    }

    public void updateStock() {
        String productIDString = productIDText.getText();
        String stockString = stockText.getText();




        Product product = null;
        ProductDAO pdao = new ProductDAO();

        if (productIDString != null && !productIDString.isBlank() && !stockString.isBlank() && stockString!= null) {


            productIDStatus.setText("");
            stockStatus.setText("");

            double stock = Double.parseDouble(stockString);

            int prodID = Integer.parseInt(productIDString);
            product = pdao.getProductByProductID(prodID);

            if (product.getUnitType() == UnitType.PIECE){
                productIDStatus.setText("Product Type : PIECE");
            }
            if (product.getUnitType() == UnitType.WEIGHT){
                productIDStatus.setText("Product Type : WEIGHT");
            }
            if (product.getUnitType() == UnitType.PIECE && stock % 2 != 0) {
                stockStatus.setText(" For PIECE type products the stock must be a whole number ! ");
            } else {

                if (stock >= 0) {
                    stockStatus.setText("");
                    productIDStatus.setText("");

                    if (product != null) {
                        product.setStock(stock);
                        pdao.updateProductStock(prodID, product);
                        JOptionPane.showMessageDialog(this, " Stock Updated Successfully ! ");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, " Product not registered  ! ");

                    }

                } else {
                    stockStatus.setText(" Stock cannot be lower than 0 ! ");
                }
            }
        } else {
            stockStatus.setText("Stock cannot be empty ! ");
            productIDStatus.setText(" Product ID cannot be empty ! ");
        }

    }
}
