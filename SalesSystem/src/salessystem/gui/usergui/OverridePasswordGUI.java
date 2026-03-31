package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class OverridePasswordGUI extends JDialog {
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    private final JLabel passwordStatus;
    private final JLabel nameStatus;
    private JTextField textfield;

    public OverridePasswordGUI(JFrame parent){
        super(parent, "Override Password", true);

        setSize(500, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel username = new JLabel("Username");
        username.setBounds(10, 20, 80, 30);
        panel.add(username);

        textfield = new JTextField(25);
        textfield.setBounds(135, 20, 165, 25);
        panel.add(textfield);


        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 50, 200, 30);
        nameStatus.setForeground(Color.red);
        panel.add(nameStatus);


        JLabel password = new JLabel(" Password ");
        password.setBounds(10, 80, 120, 30);
        panel.add(password);

        passwordField = new JPasswordField(25);
        passwordField.setBounds(135, 80, 165, 25);
        panel.add(passwordField);

        JLabel confirmPassword = new JLabel(" Confirm Password ");
        confirmPassword.setBounds(10, 110, 120, 30);
        panel.add(confirmPassword);

        confirmPasswordField = new JPasswordField(25);
        confirmPasswordField.setBounds(135, 110, 165, 25);
        panel.add(confirmPasswordField);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 195, 165, 25);
        button.addActionListener(e -> overridePassword());
        panel.add(button);

        passwordStatus = new JLabel("");
        passwordStatus.setBounds(10, 140, 600, 50);
        passwordStatus.setForeground(Color.red);
        panel.add(passwordStatus);


        }

        public void overridePassword(){
            String username = textfield.getText();
            char[] password = passwordField.getPassword();
            char[] confirmed = confirmPasswordField.getPassword();
            boolean passwordCheck = false;
            boolean usernameCheck = false;

            if (User.validatePassword(new String(password))) {

                if (Arrays.equals(password, confirmed)) {

                    passwordCheck = true;
                    passwordStatus.setText("");

                }else {
                    passwordStatus.setText(" Passwords do not match ! ");
                    confirmPasswordField.setText("");
                }

                passwordStatus.setText("");

            } else {
                if (password.length > 8){
                    passwordStatus.setText(" Password must contain at least one lowercase letter,an uppercase letter and a symbol !");
                }else{
                    passwordStatus.setText(" Password must be longer than 8 characters !");
                }

            }

            if (username != null && !username.isBlank()) {

                username = username.trim();
                nameStatus.setText("");
                usernameCheck = true;
            } else {
                nameStatus.setText(" Username cannot be blank ! ");
            }

            if (usernameCheck && passwordCheck){
                UserDAO udao = new UserDAO();
                User user = udao.getUserByUsername(username);
                user.setPassword(new String(password));

                if (user != null){
                    udao.updatePassword(user.getUserID(), user);
                    JOptionPane.showMessageDialog(this, " Password Override Successful ! ");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(this, " User not found ");
                    textfield.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                }
            }

        }
}
