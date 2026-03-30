package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;

import javax.swing.*;
import java.awt.*;

public class DeleteProductGUI extends JDialog {

    private final JTextField textField;
    private final JLabel ProductIDStatus;

    public DeleteProductGUI(JFrame parent){
        super(parent, "Delete Product",true);

        setSize(500, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel customerId = new JLabel("Product ID ");
        customerId.setBounds(10, 20, 80, 30);
        panel.add(customerId);

        textField = new JTextField(25);
        textField.setBounds(135, 20, 165, 25);
        panel.add(textField);

        ProductIDStatus = new JLabel("");
        ProductIDStatus.setBounds(10, 50, 200, 30);
        ProductIDStatus.setForeground(Color.red);
        panel.add(ProductIDStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 195, 165, 25);
        button.addActionListener(e -> deleteProduct());
        panel.add(button);


    }
    public void deleteProduct(){
        String productId = textField.getText();

        if (productId != null && !productId.isBlank()) {

            int prodID = Integer.parseInt(productId);
            ProductIDStatus.setText("");

            ProductDAO pdao = new ProductDAO();
            Product product = pdao.getProductByProductID(prodID);

            if (product != null){
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    pdao.deleteProduct(prodID);
                    JOptionPane.showMessageDialog(this, " Product deleted successfully.");
                    dispose();
                }
                else if (result == JOptionPane.NO_OPTION) {

                }

            }else {
                JOptionPane.showMessageDialog(this, " Customer not found ! ");
                textField.setText("");

            }


        } else {
            ProductIDStatus.setText(" Product ID cannot be blank ! ");
        }
    }

}
