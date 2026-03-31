package salessystem.gui.productgui;

import salessystem.dao.ProductDAO;
import salessystem.gui.customergui.GetAllCustomersGUI;
import salessystem.model.Product;
import salessystem.model.UnitType;

import javax.swing.*;
import java.awt.*;

public class AddProductsGUI extends JDialog {

    private final JTextField nameText ;
    private final JLabel nameStatus ;
    private final JTextField stockText;
    private final JLabel stockStatus;
    private final JLabel priceStatus;
    private final JTextField priceText;

    public AddProductsGUI(JFrame parent){
        super(parent, "Add New Product", true);

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

        JLabel productName = new JLabel("Product Name ");
        productName.setBounds(10, 20, 120, 30);
        parentPanel.add(productName);

        nameText = new JTextField(25);
        nameText.setBounds(140, 20, 165, 25);
        parentPanel.add(nameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 50, 500, 30);
        nameStatus.setForeground(Color.red);
        parentPanel.add(nameStatus);

        JLabel stock = new JLabel("Stock ");
        stock.setBounds(10, 80, 120, 30);
        parentPanel.add(stock);

        stockText = new JTextField(25);
        stockText.setBounds(140, 80, 165, 25);
        parentPanel.add(stockText);

        stockStatus = new JLabel("");
        stockStatus.setBounds(10, 110, 500, 30);
        stockStatus.setForeground(Color.red);
        parentPanel.add(stockStatus);

        JLabel price = new JLabel("Price (Rs.) ");
        price.setBounds(10, 140, 120, 30);
        parentPanel.add(price);

        priceText = new JTextField(25);
        priceText.setBounds(140, 140, 165, 25);
        parentPanel.add(priceText);

        priceStatus = new JLabel("");
        priceStatus.setBounds(10, 170, 500, 30);
        priceStatus.setForeground(Color.red);
        parentPanel.add(priceStatus);

        JButton button = new JButton(" Add ");
        button.setBounds(10, 200, 165, 25);
        button.addActionListener(e -> {
            newProduct();

            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllProductsGUI.productsTable(panel);
        });
        parentPanel.add(button);

    }

    public void newProduct(){
        Product product = null;
        ProductDAO pdao = new ProductDAO();

        String name = nameText.getText();
        boolean nameCheck = false;

        String priceString = priceText.getText();
        boolean priceCheck = false;
        double price = Double.parseDouble(priceString);

        String stockString = stockText.getText();
        boolean stockCheck = false;
        double stock = Double.parseDouble(stockString);

        if (name !=null && !name.isBlank()){
            nameCheck = true;
            nameStatus.setText("");
        } else {
            nameStatus.setText(" Customer name cannot be empty ! ");
        }

        if (priceString !=null && !priceString.isBlank()){
            if(price < 0){
                priceStatus.setText(" Price cannot be lower than 0 ! ");
            } else {
                priceCheck = true;
            }

            priceStatus.setText("");
        } else {
            priceStatus.setText(" Price name cannot be empty ! ");
        }
        if (stockString !=null && !stockString.isBlank()){
            if (stock < 0){
                stockStatus.setText(" Stock cannot be lower than 0 ! ");
            }else {
                stockCheck = true;
            }

            stockStatus.setText("");
        } else {
            stockStatus.setText(" stock cannot be empty ! ");
        }
        if (nameCheck && priceCheck && stockCheck){
            String[] options = {"PIECE", "WEIGHT"};

            int choice = JOptionPane.showOptionDialog(
                    //checks role
                    null,
                    "Select Unit Type:",
                    "Unit Type Selection",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );
            if (choice == 0){
                product = new Product(name, UnitType.PIECE, price, stock);
                pdao.addProduct(product);
                JOptionPane.showMessageDialog(this, " Product (PIECE) Added Successfully ! ");
                nameText.setText("");
                priceText.setText("");
                stockText.setText("");
            }
            if (choice == 1){
                product = new Product(name, UnitType.WEIGHT, price, stock);
                pdao.addProduct(product);
                JOptionPane.showMessageDialog(this, " Product (WEIGHT) Added Successfully ! ");
                nameText.setText("");
                priceText.setText("");
                stockText.setText("");
            }
            if (product == null){
                JOptionPane.showMessageDialog(this, " Error occurred while adding product ! ");
                nameText.setText("");
                priceText.setText("");
                stockText.setText("");

            }

        }

    }
}
