package salessystem.gui;

import salessystem.Global;
import salessystem.gui.dashboards.AdminDashboard;
import salessystem.gui.dashboards.ClerkDashboard;
import salessystem.gui.usergui.LoginGUI;

import javax.swing.*;
import java.awt.*;

//provides the JFrame
public class GUI extends JFrame {

    //initiates the JFrame
    //includes a click anywhere to start window
    public GUI() {

        setSize(1280,720);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        JButton button = new JButton(Global.companyName);
        button.setBounds(10,110,80,25);
        button.setFont(new Font("Arial", Font.BOLD, 50));
        button.addActionListener(e-> {

            LoginGUI loginGUI = new LoginGUI(this);
            loginGUI.setVisible(true);

            //if logged in
            if (loginGUI.getAuthentication()) {

                //if root user is logged in
                if (loginGUI.getRootAccess()) {
                    AdminDashboard adminDashboard = new AdminDashboard(this);
                    adminDashboard.setVisible(true);
                } else {

                    //if an Admin logged in
                    if (loginGUI.getLoggedUser().getRole().equals("Admin")) {
                        AdminDashboard adminDashboard = new AdminDashboard(this);
                        adminDashboard.setVisible(true);
                    }

                    //if a Clerk logged in
                    if (loginGUI.getLoggedUser().getRole().equals("Clerk")) {
                        ClerkDashboard clerkDashboard = new ClerkDashboard(this);
                        clerkDashboard.setVisible(true);
                    }
                }
            }
        });
        add(button);
        setVisible(true);
    }
}
