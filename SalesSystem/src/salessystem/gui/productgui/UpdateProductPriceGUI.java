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
        super(parent, "Update The Contact Number", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel productId = new JLabel("Product ID ");
        productId.setBounds(10, 20, 120, 30);
        panel.add(productId);

        productIDText= new JTextField(25);
        productIDText.setBounds(120, 20, 165, 25);
        panel.add(productIDText);

        productIDStatus = new JLabel("");
        productIDStatus.setBounds(10, 50, 500, 30);
        productIDStatus.setForeground(Color.red);
        panel.add(productIDStatus);

        JLabel price = new JLabel("Price ");
        price.setBounds(10, 80, 120, 30);
        panel.add(price);

        priceText = new JTextField(25);
        priceText.setBounds(120, 80, 165, 25);
        panel.add(priceText);

        priceStatus = new JLabel("");
        priceStatus.setBounds(10, 110, 500, 30);
        priceStatus.setForeground(Color.red);
        panel.add(priceStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);
        button.addActionListener(e -> updateStock());
        panel.add(button);


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
                    product.setStock(price);
                    pdao.updateProductStock(prodID, product);
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
