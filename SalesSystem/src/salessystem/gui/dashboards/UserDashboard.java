package salessystem.gui.dashboards;

import javax.swing.*;

//the dashboard accessed by a user
public interface UserDashboard {

    void openManageUsersDialog(JFrame parent);//method that creates the manage users JDialog
    void showManageUsers(JPanel panel, JFrame parent);//the button layout in the manage user window

    void openManageCustomersDialog(JFrame parent);//method that creates the manage customers JDialog
    void showManageCustomers(JPanel panel, JFrame parent);//button layout of manage customers window

    void openManageProductsDialog(JFrame parent);//method that creates the manage products dialog
    void showManageProducts(JPanel panel, JFrame parent);//button layout of the manage products window

    void openManageSalesDialog(JFrame parent);//method that creates the manage sales JDialog
    void showManageSales(JPanel panel, JFrame parent);//button layout of the manage sales window


}
