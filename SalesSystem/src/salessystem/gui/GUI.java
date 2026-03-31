package salessystem.gui;

import salessystem.gui.dashboards.AdminDashboard;
import salessystem.gui.dashboards.ClerkDashboard;
import salessystem.gui.usergui.LoginGUI;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {

        setSize(1280,720);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        JButton button = new JButton("Click anywhere to Start !");
        button.setBounds(10,110,80,25);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.addActionListener(e-> {

            LoginGUI loginGUI = new LoginGUI(this);
            loginGUI.setVisible(true);

            if (loginGUI.getAuthentication()) {
                if (loginGUI.getRootAccess()) {
                    AdminDashboard adminDashboard = new AdminDashboard(this);
                    adminDashboard.setVisible(true);
                } else {
                    if (loginGUI.getLoggedUser().getRole().equals("Admin")) {
                        AdminDashboard adminDashboard = new AdminDashboard(this);
                        adminDashboard.setVisible(true);
                    }
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
