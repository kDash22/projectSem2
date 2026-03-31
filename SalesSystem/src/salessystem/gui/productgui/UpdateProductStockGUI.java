package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;
import salessystem.model.UnitType;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for updating product stock
public class UpdateProductStockGUI extends JDialog {

    private final JTextField productIDText;//input field for product id
    private final JLabel productIDStatus;//displays errors regarding product id
    private final JTextField stockText;//input field for product stock
    private final JLabel stockStatus;//displays errors regarding product stock

    //initialise the JDialog
    //includes
    // - product info form (left)
    // - product table (right)
    // - confirm button
    public UpdateProductStockGUI(JFrame parent) {
        super(parent, "Update Product Stock", true);

        setSize(1000, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //table panel
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

        //handles the updating and UI refresh
        button.addActionListener(e -> {
            updateStock();

            //table refresh
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllProductsGUI.productsTable(panel);
        });
        parentPanel.add(button);


    }

    //validates the info
    //updates the database using the DAO
    public void updateStock() {
        String productIDString = productIDText.getText();
        String stockString = stockText.getText();

        Product product = null;
        ProductDAO pdao = new ProductDAO();

        //all not empty or blank
        if (productIDString != null && !productIDString.isBlank() && !stockString.isBlank() && stockString != null) {

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

            //if user tries to assign a decimal point number for a piece unit type
            if (product.getUnitType() == UnitType.PIECE && stock % 2 != 0) {
                stockStatus.setText(" For PIECE type products the stock must be a whole number ! ");
            } else {

                if (stock >= 0) {
                    stockStatus.setText("");
                    productIDStatus.setText("");

                    //if product exists
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
