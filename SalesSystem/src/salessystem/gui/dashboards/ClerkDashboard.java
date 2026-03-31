package salessystem.gui.dashboards;

import salessystem.gui.customergui.*;
import salessystem.gui.productgui.*;
import salessystem.gui.salesgui.*;

import javax.swing.*;

//provides the JDialog for clerk dashboard
public class ClerkDashboard extends JDialog implements UserDashboard {

    //initialise the JDialog
    //includes buttons to choose
    public ClerkDashboard(JFrame parent){
        super(parent, " Clerk Dashboard ",true);

        setSize(850,300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JButton b1 = new JButton(" Manage Customers ");
        b1.setBounds(170,130,150,40);

        JButton b2 = new JButton(" Manage Products ");
        b2.setBounds(360,130,150,40);

        JButton b3 = new JButton(" Manage Sales ");
        b3.setBounds(550,130,150,40);

        //manages which JDialog gets activated
        b1.addActionListener(e -> openManageCustomersDialog(parent));
        b2.addActionListener(e -> openManageProductsDialog(parent));
        b3.addActionListener(e -> openManageSalesDialog(parent));

        panel.add(b1);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);


    }

    @Override
    public void openManageUsersDialog(JFrame parent) {
        //not available for Clerk role
    }

    @Override
    public void showManageUsers(JPanel panel, JFrame parent) {
        //not available for Clerk role
    }

    public void openManageCustomersDialog(JFrame parent){
        JDialog dialog = new JDialog(parent, "Manage Customers", true);
        dialog.setSize(990,300);
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

    }    public void openManageProductsDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Products ", true);
        dialog.setSize(650,300);
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

    }
    public void openManageSalesDialog(JFrame parent){

        JDialog dialog = new JDialog(parent, "Manage Sales", true);
        dialog.setSize(990,300);
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

        JButton printInvoice = new JButton("Print Invoice");
        printInvoice.setBounds(770,130,150,40);
        printInvoice.addActionListener(e-> {
            PrintInvoiceGUI.printInvoiceButton(parent);
        });
        panel.add(printInvoice);

    }

}
