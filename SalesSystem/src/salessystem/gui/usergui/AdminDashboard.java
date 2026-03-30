package salessystem.gui.usergui;

import salessystem.gui.customergui.*;
import salessystem.gui.productgui.*;

import javax.swing.*;

public class AdminDashboard extends JDialog{


    public AdminDashboard(JFrame parent){
        super(parent, " Admin Dashboard ",true);

        setSize(900,300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel manageUsers = new JLabel(" Manage Users ");
        manageUsers.setBounds(10,20,180,30);
        panel.add(manageUsers);

        JButton button = new JButton(" Add New User ");
        button.setBounds(10,50,180,25);
        button.addActionListener(e-> {
            AddNewUserGUI newAdminGUI =new AddNewUserGUI(parent);
            newAdminGUI.setVisible(true);
        } );
        panel.add(button);

        JButton searchUsers = new JButton("Search Users");
        searchUsers.setBounds(10,80,180,25);
        searchUsers.addActionListener(e-> {
            SearchUsersGUI searchUsersGUI = new SearchUsersGUI(parent);

            if (searchUsersGUI.getChoice() != -1) {
                searchUsersGUI.setVisible(true);
            }
        });
        panel.add(searchUsers);

        JButton overridePassword = new JButton("Override Password");
        overridePassword.setBounds(10,110,180,25);
        overridePassword.addActionListener(e-> {
            OverridePasswordGUI overridePasswordGUI = new OverridePasswordGUI(parent);
            overridePasswordGUI.setVisible(true);
        });
        panel.add(overridePassword);

        JButton changeUsersName = new JButton("Change User's name");
        changeUsersName.setBounds(10,140,180,25);
        changeUsersName.addActionListener(e-> {
            ChangeUsersNamesGUI changeUsersNamesGUI = new ChangeUsersNamesGUI(parent);
            changeUsersNamesGUI.setVisible(true);
        });
        panel.add(changeUsersName);

        JButton deleteUser = new JButton("Delete User");
        deleteUser.setBounds(10,170,180,25);
        deleteUser.addActionListener(e-> {
            DeleteUserGUI deleteUserGUI = new DeleteUserGUI(parent);
            deleteUserGUI.setVisible(true);
        });
        panel.add(deleteUser);

        JButton showAllUsers = new JButton("Show All Users");
        showAllUsers.setBounds(10,200,180,25);
        showAllUsers.addActionListener(e-> {
            GetAllUserGUI getAllUserGUI = new GetAllUserGUI(parent);
            getAllUserGUI.setVisible(true);
        });
        panel.add(showAllUsers);

        JLabel manageCustomers = new JLabel(" Manage Customers ");
        manageCustomers.setBounds(200,20,180,30);
        panel.add(manageCustomers);

        JButton addCustomer = new JButton("Add Customer");
        addCustomer.setBounds(200,50,180,25);
        addCustomer.addActionListener(e-> {
            AddCustomerGUI addCustomerGUI = new AddCustomerGUI(parent);
            addCustomerGUI.setVisible(true);
        });
        panel.add(addCustomer);

        JButton searchCustomer = new JButton("Search Customer");
        searchCustomer.setBounds(200,80,180,25);
        searchCustomer.addActionListener(e-> {
            SearchCustomersGUI searchCustomersGUI = new SearchCustomersGUI(parent);
            searchCustomersGUI.setVisible(true);
        });
        panel.add(searchCustomer);

        JButton showAllCustomers = new JButton("Show All Customers");
        showAllCustomers.setBounds(200,110,180,25);
        showAllCustomers.addActionListener(e-> {
            GetAllCustomersGUI getAllCustomersGUI = new GetAllCustomersGUI(parent);
            getAllCustomersGUI.setVisible(true);
        });
        panel.add(showAllCustomers);

        JButton updateCustomerContactNo = new JButton("Update Customer Contact");
        updateCustomerContactNo.setBounds(200,140,180,25);
        updateCustomerContactNo.addActionListener(e-> {
            UpdateCustomerContactNoGUI gui = new UpdateCustomerContactNoGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updateCustomerContactNo);

        JButton updateCustomer = new JButton("Update Customer ");
        updateCustomer.setBounds(200,170,180,25);
        updateCustomer.addActionListener(e-> {
            UpdateCustomerGUi gui = new UpdateCustomerGUi(parent);
            gui.setVisible(true);
        });
        panel.add(updateCustomer);

        JButton deleteCustomer = new JButton("Delete Customer ");
        deleteCustomer.setBounds(200,200,180,25);
        deleteCustomer.addActionListener(e-> {
            DeleteCustomerGUI gui = new DeleteCustomerGUI(parent);
            gui.setVisible(true);
        });
        panel.add(deleteCustomer);

        JLabel manageProducts = new JLabel(" Manage Products ");
        manageProducts.setBounds(390,20,180,30);
        panel.add(manageProducts);

        JButton addProduct = new JButton("Add Product");
        addProduct.setBounds(390,50,180,25);
        addProduct.addActionListener(e-> {
            AddProductsGUI gui = new AddProductsGUI(parent);
            gui.setVisible(true);
        });
        panel.add(addProduct);

        JButton searchProduct = new JButton("Search Product");
        searchProduct.setBounds(390,80,180,25);
        searchProduct.addActionListener(e-> {
            SearchProductGUI gui = new SearchProductGUI(parent);
            gui.setVisible(true);
        });
        panel.add(searchProduct);

        JButton showAllProducts = new JButton("Show All Products");
        showAllProducts.setBounds(390,110,180,25);
        showAllProducts.addActionListener(e-> {
            GetAllProductsGUI gui = new GetAllProductsGUI(parent);
            gui.setVisible(true);
        });
        panel.add(showAllProducts);

        JButton updateStock = new JButton("Update Product Stock");
        updateStock.setBounds(390,140,180,25);
        updateStock.addActionListener(e-> {
            UpdateProductStockGUI gui = new UpdateProductStockGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updateStock);

        JButton updatePrice = new JButton("Update Product Price");
        updatePrice.setBounds(390,170,180,25);
        updatePrice.addActionListener(e-> {
            UpdateProductPriceGUI gui = new UpdateProductPriceGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updatePrice);

        JButton deleteProduct = new JButton("Delete Product ");
        deleteProduct.setBounds(390,200,180,25);
        deleteProduct.addActionListener(e-> {
            DeleteProductGUI gui = new DeleteProductGUI(parent);
            gui.setVisible(false);
        });
        panel.add(deleteProduct);

        JLabel manageSales = new JLabel(" Manage Sales ");
        manageSales.setBounds(580,20,180,30);
        panel.add(manageSales);



















    }
}
