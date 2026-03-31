package salessystem.gui.usergui;

import salessystem.Global;
import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JDialog {

    private boolean authentication = false;
    private User loggedUser;
    private boolean rootAccess;

    private JTextField textfield;
    private JPasswordField passwordField;
    private JLabel status;

    public LoginGUI(JFrame parent) {
        super(parent, "Login", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,30);
        panel.add(userLabel);

        textfield = new JTextField(25);
        textfield.setBounds(100,20,165,25);
        panel.add(textfield);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,70,80,30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(25);
        passwordField.setBounds(100,70,165,25);
        panel.add(passwordField);

        JButton button = new JButton("Login");
        button.setBounds(10,110,80,25);
        button.addActionListener(e-> {
            verifyLogin();
        });
        panel.add(button);

        status = new JLabel("");
        status.setBounds(10,40,300,25);
        status.setForeground(Color.RED);
        panel.add(status);

    }

    private void verifyLogin() {
        String username = textfield.getText();
        char[] password = passwordField.getPassword();

        if(Global.rootPassword.equals(new String(password)) && Global.rootUser.equals(username)) {
            JOptionPane.showMessageDialog(this, " Welcome Mr.Stark ! ");
            authentication = true;
            rootAccess = true;
            dispose();
        }

        UserDAO udao = new UserDAO();
        User user = udao.getUserByUsername(username);

        if (user != null){
            status.setText("");
            if(user.getPassword().equals(new String(password))) {
               JOptionPane.showMessageDialog(this, " Login Successful ! ");

               authentication = true;
               loggedUser = user;
               dispose();

            } else {
                passwordField.setText("");
                JOptionPane.showMessageDialog(this, "Login Failed ! ");
            }
        } else {
            status.setText(" There is no user with that username ! ");
            passwordField.setText("");
        }
    }

    public boolean getAuthentication() {
        return authentication;
    }

    public User getLoggedUser(){
        return loggedUser;
    }

    public boolean getRootAccess(){
        return rootAccess;
    }
}
