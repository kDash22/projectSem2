package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.Admin;
import salessystem.model.Clerk;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AddNewUserGUI extends JDialog {

    private JTextField firstNameText;
    private JTextField lastNameText;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel passwordStatus;
    private JLabel nameStatus;


    public AddNewUserGUI(JFrame parent) {
        super(parent, "Add New User", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel firstName = new JLabel("First Name ");
        firstName.setBounds(10, 20, 80, 30);
        panel.add(firstName);

        firstNameText = new JTextField(25);
        firstNameText.setBounds(100, 20, 165, 25);
        panel.add(firstNameText);

        JLabel lastName = new JLabel("Last Name ");
        lastName.setBounds(10, 50, 80, 30);
        panel.add(lastName);

        lastNameText = new JTextField(25);
        lastNameText.setBounds(100, 50, 165, 25);
        panel.add(lastNameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 80, 200, 30);
        nameStatus.setForeground(Color.red);
        panel.add(nameStatus);


        JLabel password = new JLabel(" Password ");
        password.setBounds(10, 110, 80, 30);
        panel.add(password);

        passwordField = new JPasswordField(25);
        passwordField.setBounds(100, 110, 165, 25);
        panel.add(passwordField);

        JLabel confirmPassword = new JLabel(" Password ");
        confirmPassword.setBounds(10, 140, 80, 30);
        panel.add(confirmPassword);

        confirmPasswordField = new JPasswordField(25);
        confirmPasswordField.setBounds(100, 140, 165, 25);
        panel.add(confirmPasswordField);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 225, 165, 25);
        button.addActionListener(e -> newUser());
        panel.add(button);

        passwordStatus = new JLabel("");
        passwordStatus.setBounds(10, 170, 600, 50);
        passwordStatus.setForeground(Color.red);
        panel.add(passwordStatus);

    }

    private void newUser() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        char[] password = passwordField.getPassword();
        char[] confirmed = confirmPasswordField.getPassword();
        boolean passwordCheck = false;
        boolean nameCheck = false;




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

        if (firstName != null && !firstName.isBlank()
                && lastName != null && !lastName.isBlank()) {

            firstName = firstName.trim();
            lastName = lastName.trim();

            firstName = firstName.substring(0, 1).toUpperCase()
                    + firstName.substring(1).toLowerCase();
            lastName = lastName.substring(0, 1).toUpperCase()
                    + lastName.substring(1).toLowerCase();
            nameCheck = true;
            nameStatus.setText("");
        } else {
            nameStatus.setText(" Names cannot be blank ! ");

        }

        if (nameCheck && passwordCheck){

            String[] options = {"Admin", "Clerk"};

            int choice = JOptionPane.showOptionDialog(
                    //checks role
                    null,
                    "Select role:",
                    "Role Selection",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            UserDAO udao = new UserDAO();
            User user;

            if (choice == 0) {
                user = new Admin(firstName, lastName, new String(password));
                udao.addUser(user);
                JOptionPane.showMessageDialog(this, "New Admin Created Successfully ! ");
                dispose();

            } else if (choice == 1) {
                user = new Clerk(firstName, lastName, new String(password));
                udao.addUser(user);
                JOptionPane.showMessageDialog(this, "New Clerk Created Successfully ! ");
                dispose();

            }

            Arrays.fill(password, '\0'); //password erasure
            Arrays.fill(confirmed, '\0');
        }



    }
}

