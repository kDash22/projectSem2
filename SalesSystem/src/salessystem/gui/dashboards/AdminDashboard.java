package salessystem.gui.dashboards;

import salessystem.gui.customergui.*;
import salessystem.gui.productgui.*;
import salessystem.gui.salesgui.*;
import salessystem.gui.usergui.*;

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

        JButton b1 = new JButton("Manage Users");
        b1.setBounds(90,130,150,40);

        JButton b2 = new JButton("Manage Customers");
        b2.setBounds(260,130,150,40);

        JButton b3 = new JButton("Manage Products ");
        b3.setBounds(430,130,150,40);

        JButton b4 = new JButton("Manage Sales");
        b4.setBounds(600,130,150,40);


        b1.addActionListener(e -> openManageUsersDialog(parent));
        b2.addActionListener(e -> openManageCustomersDialog(parent));
        b3.addActionListener(e -> openManageProductsDialog(parent));
        b4.addActionListener(e -> openManageSalesDialog(parent));

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);


    }
    public void openManageUsersDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Users", true);
        dialog.setSize(1160,300);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        dialog.add(panel);

        showManageUsers(panel, parent);

        dialog.setVisible(true);
    }
    public void showManageUsers(JPanel panel, JFrame parent){;

        JButton button = new JButton(" Add New User ");
        button.setBounds(90,130,150,40);
        button.addActionListener(e -> {
            new AddNewUserGUI(parent).setVisible(true);
        });
        panel.add(button);

        JButton searchUsers = new JButton("Search Users");
        searchUsers.setBounds(260,130,150,40);
        searchUsers.addActionListener(e -> {
            SearchUsersGUI gui = new SearchUsersGUI(parent);
            if (gui.getChoice() != -1) {
                gui.setVisible(true);
            }
        });
        panel.add(searchUsers);

        JButton overridePassword = new JButton("Override Password");
        overridePassword.setBounds(430,130,150,40);
        overridePassword.addActionListener(e -> {
            new OverridePasswordGUI(parent).setVisible(true);
        });
        panel.add(overridePassword);

        JButton changeUsersName = new JButton("Change Name");
        changeUsersName.setBounds(600,130,150,40);
        changeUsersName.addActionListener(e -> {
            new ChangeUsersNamesGUI(parent).setVisible(true);
        });
        panel.add(changeUsersName);

        JButton deleteUser = new JButton("Delete User");
        deleteUser.setBounds(770,130,150,40);
        deleteUser.addActionListener(e -> {
            new DeleteUserGUI(parent).setVisible(true);
        });
        panel.add(deleteUser);

        JButton showAllUsers = new JButton("Show All Users");
        showAllUsers.setBounds(940,130,150,40);
        showAllUsers.addActionListener(e -> {
            new GetAllUserGUI(parent).setVisible(true);
        });
        panel.add(showAllUsers);

        panel.revalidate();
        panel.repaint();
    }
    public void openManageCustomersDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Customers", true);
        dialog.setSize(1160,300);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        dialog.add(panel);

        showManageCustomers(panel, parent);

        dialog.setVisible(true);
    }

    public void showManageCustomers(JPanel panel, JFrame parent){

        JButton addCustomer = new JButton("Add Customer");
        addCustomer.setBounds(90,130,150,40);
        addCustomer.addActionListener(e-> {
            AddCustomerGUI addCustomerGUI = new AddCustomerGUI(parent);
            addCustomerGUI.setVisible(true);
        });
        panel.add(addCustomer);

        JButton searchCustomer = new JButton("Search Customer");
        searchCustomer.setBounds(260,130,150,40);
        searchCustomer.addActionListener(e-> {
            SearchCustomersGUI searchCustomersGUI = new SearchCustomersGUI(parent);
            searchCustomersGUI.setVisible(true);
        });
        panel.add(searchCustomer);

        JButton showAllCustomers = new JButton("Show All Customers");
        showAllCustomers.setBounds(430,130,150,40);
        showAllCustomers.addActionListener(e-> {
            GetAllCustomersGUI getAllCustomersGUI = new GetAllCustomersGUI(parent);
            getAllCustomersGUI.setVisible(true);
        });
        panel.add(showAllCustomers);

        JButton updateCustomerContactNo = new JButton("Update Contact");
        updateCustomerContactNo.setBounds(600,130,150,40);
        updateCustomerContactNo.addActionListener(e-> {
            UpdateCustomerContactNoGUI gui = new UpdateCustomerContactNoGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updateCustomerContactNo);

        JButton updateCustomer = new JButton("Update Customer ");
        updateCustomer.setBounds(770,130,150,40);
        updateCustomer.addActionListener(e-> {
            UpdateCustomerGUi gui = new UpdateCustomerGUi(parent);
            gui.setVisible(true);
        });
        panel.add(updateCustomer);

        JButton deleteCustomer = new JButton("Delete Customer ");
        deleteCustomer.setBounds(940,130,150,40);
        deleteCustomer.addActionListener(e-> {
            DeleteCustomerGUI gui = new DeleteCustomerGUI(parent);
            gui.setVisible(true);
        });
        panel.add(deleteCustomer);

    }    public void openManageProductsDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Products ", true);
        dialog.setSize(1160,300);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        dialog.add(panel);

        showManageProducts(panel, parent);

        dialog.setVisible(true);
    }
    public void showManageProducts(JPanel panel, JFrame parent){


        JButton addProduct = new JButton("Add Product");
        addProduct.setBounds(90,130,150,40);
        addProduct.addActionListener(e-> {
            AddProductsGUI gui = new AddProductsGUI(parent);
            gui.setVisible(true);
        });
        panel.add(addProduct);

        JButton searchProduct = new JButton("Search Product");
        searchProduct.setBounds(260,130,150,40);
        searchProduct.addActionListener(e-> {
            SearchProductGUI gui = new SearchProductGUI(parent);
            gui.setVisible(true);
        });
        panel.add(searchProduct);

        JButton showAllProducts = new JButton("Show All Products");
        showAllProducts.setBounds(430,130,150,40);
        showAllProducts.addActionListener(e-> {
            GetAllProductsGUI gui = new GetAllProductsGUI(parent);
            gui.setVisible(true);
        });
        panel.add(showAllProducts);

        JButton updateStock = new JButton("Update Stock");
        updateStock.setBounds(600,130,150,40);
        updateStock.addActionListener(e-> {
            UpdateProductStockGUI gui = new UpdateProductStockGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updateStock);

        JButton updatePrice = new JButton("Update Price");
        updatePrice.setBounds(770,130,150,40);
        updatePrice.addActionListener(e-> {
            UpdateProductPriceGUI gui = new UpdateProductPriceGUI(parent);
            gui.setVisible(true);
        });
        panel.add(updatePrice);

        JButton deleteProduct = new JButton("Delete Product ");
        deleteProduct.setBounds(940,130,150,40);
        deleteProduct.addActionListener(e-> {
            DeleteProductGUI gui = new DeleteProductGUI(parent);
            gui.setVisible(false);
        });





    }
    public void openManageSalesDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Sales", true);
        dialog.setSize(1160,300);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        dialog.add(panel);

        showManageSales(panel, parent);

        dialog.setVisible(true);
    }

    public void showManageSales(JPanel panel, JFrame parent){

        JButton addSale = new JButton("Add Sale ");
        addSale.setBounds(90,130,150,40);
        addSale.addActionListener(e-> {
            AddSaleGUI gui = new AddSaleGUI(parent);
            gui.setVisible(true);
        });
        panel.add(addSale);

        JButton searchSale = new JButton("Search Sale ");
        searchSale.setBounds(260,130,150,40);
        searchSale.addActionListener(e-> {
            SearchSaleGUI gui = new SearchSaleGUI(parent);
            gui.setVisible(true);
        });
        panel.add(searchSale);

        JButton showAllSales = new JButton("Show all Sales ");
        showAllSales.setBounds(430,130,150,40);
        showAllSales.addActionListener(e-> {
            GetAllSalesGUI gui = new GetAllSalesGUI(parent);
            gui.setVisible(true);
        });
        panel.add(showAllSales);

        JButton showAllSaleItems = new JButton("Show all Sale Items");
        showAllSaleItems.setBounds(600,130,150,40);
        showAllSaleItems.addActionListener(e-> {
            GetAllSaleItemsGUI gui = new GetAllSaleItemsGUI(parent);
            gui.setVisible(true);
        });
        panel.add(showAllSaleItems);

        JButton deleteSale = new JButton("Delete Sale");
        deleteSale.setBounds(770,130,150,40);
        deleteSale.addActionListener(e-> {
            DeleteSaleGUI gui = new DeleteSaleGUI(parent);
            gui.setVisible(true);
        });
        panel.add(deleteSale);

        JButton printInvoice = new JButton("Print Invoice");
        printInvoice.setBounds(940,130,150,40);
        printInvoice.addActionListener(e-> {
            PrintInvoiceGUI.printInvoiceButton(parent);
        });
        panel.add(printInvoice);

    }
}
