package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.model.Product;

import javax.swing.*;
import java.awt.*;

public class SearchProductGUI extends JDialog {

    private final JTextField textfield;
    private JLabel msg = new JLabel("");
    private JLabel productID = new JLabel("");
    private JLabel productName = new JLabel("");
    private JLabel price = new JLabel("");
    private JLabel stock = new JLabel("");
    private JLabel unitType = new JLabel("");

    public SearchProductGUI(JFrame parent){

        super(parent, " Search Products ",true);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel userLabel = new JLabel("Product ID ");
        userLabel.setBounds(10,20,80,30);
        panel.add(userLabel);

        textfield = new JTextField(25);
        textfield.setBounds(100,20,165,25);
        panel.add(textfield);

        JButton button = new JButton("Search");
        button.setBounds(10,50,80,25);
        button.addActionListener(e-> searchProduct(panel));
        panel.add(button);


    }

    public void searchProduct(JPanel panel){

        String prodID = textfield.getText();
        Product product = null;
        ProductDAO pdao = new ProductDAO();

        if (prodID != null && !prodID.isBlank()){
            int prodId = Integer.parseInt(prodID);
            product = pdao.getProductByProductID(prodId);}

        if(product != null){

            msg.setText("");
            productID.setText("");
            productName.setText("");
            price.setText("");
            stock.setText("");
            unitType.setText("");


            msg.setText("Product Found !");
            msg.setForeground(Color.black);
            msg.setBounds(10,100,400,25);

            productID.setText("Product ID: "+ product.getProductId());
            productID.setBounds(10,130,400,25);

            productName.setText("Product Name : "+product.getProductName());
            productName.setBounds(10,160,400,25);

            price.setText("Price : "+product.getPrice());
            price.setBounds(10,190,400,25);

            stock.setText("Stock : "+product.getStock());
            stock.setBounds(10,220,400,25);

            unitType.setText("Unit Type : "+product.getUnitType().name());
            unitType.setBounds(10,250,400,25);

            panel.add(msg);
            panel.add(productID);
            panel.add(productName);
            panel.add(price);
            panel.add(stock);
            panel.add(unitType);

        }else {
            msg.setText("");
            productID.setText("");
            productName.setText("");
            price.setText("");
            stock.setText("");
            unitType.setText("");

            msg.setText("Product Not found !");
            msg.setBounds(10,100,400,25);
            msg.setForeground(Color.red);
            panel.add(msg);

        }
        panel.revalidate();
        panel.repaint();

    }

}
