package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;

import javax.swing.*;
import java.awt.*;

public class UpdateProductPriceGUI extends JDialog {

    private final JTextField productIDText;
    private final JLabel productIDStatus;
    private final JTextField priceText;
    private final JLabel priceStatus;

    public UpdateProductPriceGUI(JFrame parent){
        super(parent, "Update Product Price", true);

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

        productIDText= new JTextField(25);
        productIDText.setBounds(120, 20, 165, 25);
        parentPanel.add(productIDText);

        productIDStatus = new JLabel("");
        productIDStatus.setBounds(10, 50, 500, 30);
        productIDStatus.setForeground(Color.red);
        parentPanel.add(productIDStatus);

        JLabel price = new JLabel("Price (Rs.) ");
        price.setBounds(10, 80, 120, 30);
        parentPanel.add(price);

        priceText = new JTextField(25);
        priceText.setBounds(120, 80, 165, 25);
        parentPanel.add(priceText);

        priceStatus = new JLabel("");
        priceStatus.setBounds(10, 110, 500, 30);
        priceStatus.setForeground(Color.red);
        parentPanel.add(priceStatus);

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
    public void updateStock(){
        String productIDString = productIDText.getText();
        String priceString = priceText.getText();
        double price = Double.parseDouble(priceString);

        Product product = null;
        ProductDAO pdao = new ProductDAO();

        if(productIDString !=null && !productIDString.isBlank()){

            productIDStatus.setText("");
            priceStatus.setText("");

            int prodID = Integer.parseInt(productIDString);
            product = pdao.getProductByProductID(prodID);

            if (price >= 0){
                priceStatus.setText("");
                productIDStatus.setText("");

                if (product != null){
                    product.setPrice(price);
                    pdao.updateProductPrice(prodID, product);
                    JOptionPane.showMessageDialog(this, " Price Updated Successfully ! ");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, " Product not registered  ! ");

                }

            } else {
                priceStatus.setText(" Price cannot be lower than 0 ! ");
            }
        }else {
            productIDStatus.setText(" Product ID cannot be empty ! ");
        }



    }
}
