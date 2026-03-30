package salessystem.gui;

import salessystem.gui.usergui.AdminDashboard;
import salessystem.gui.usergui.LoginGUI;

import javax.swing.*;

public class GUI extends JFrame {

    private JButton openLogin;
    public GUI(){

        setTitle(" Sales System");
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        openLogin = new JButton("To Login");
        openLogin.setBounds(200,300,100,50);

        openLogin.addActionListener(e-> {
            LoginGUI loginGUI = new LoginGUI(this);
            //loginGUI.setVisible(true);

            AdminDashboard adminDashboard =new AdminDashboard(this);
            adminDashboard.setVisible(true);



            if (loginGUI.getAuthentication()){
                if (loginGUI.getLoggedUser().getRole().equals("Admin")){
                    openLogin.setVisible(false);
                    //AdminDashboard adminDashboard =new AdminDashboard(this);
                    //adminDashboard.setVisible(true);
                }
            }
        });
        add(openLogin);

        setVisible(true);
    }

    static void main(String[] args) {
        new GUI().setVisible(true);
    }


}
