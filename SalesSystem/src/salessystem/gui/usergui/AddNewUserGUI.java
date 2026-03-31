package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.Admin;
import salessystem.model.Clerk;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

//provides the JDialog for adding a new user
public class AddNewUserGUI extends JDialog {

    private JTextField firstNameText;//input field for first name
    private JTextField lastNameText;//input field for last name
    private JPasswordField passwordField;//password field for password
    private JPasswordField confirmPasswordField;//password field for confirm password
    private JLabel passwordStatus;//displays errors related to password
    private JLabel nameStatus;//display errors related to name

    //initialise the JDialog
    //includes
    // - user info form (left)
    // - user table (left)
    // - confirm button
    public AddNewUserGUI(JFrame parent) {
        super(parent, "Add New User", true);

        setSize(800, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //table panel
        JPanel panel = new JPanel();
        panel.setBounds(300, 20, 550,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllUserGUI.UserTable(panel);

        JLabel firstName = new JLabel("First Name ");
        firstName.setBounds(10, 20, 120, 30);
        parentPanel.add(firstName);

        firstNameText = new JTextField(25);
        firstNameText.setBounds(140, 20, 165, 25);
        parentPanel.add(firstNameText);

        JLabel lastName = new JLabel("Last Name ");
        lastName.setBounds(10, 50, 120, 30);
        parentPanel.add(lastName);

        lastNameText = new JTextField(25);
        lastNameText.setBounds(140, 50, 165, 25);
        parentPanel.add(lastNameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 80, 200, 30);
        nameStatus.setForeground(Color.red);
        parentPanel.add(nameStatus);


        JLabel password = new JLabel(" Password ");
        password.setBounds(10, 110, 120, 30);
        parentPanel.add(password);

        passwordField = new JPasswordField(25);
        passwordField.setBounds(140, 110, 165, 25);
        parentPanel.add(passwordField);

        JLabel confirmPassword = new JLabel("Confirm Password ");
        confirmPassword.setBounds(10, 140, 120, 30);
        parentPanel.add(confirmPassword);

        confirmPasswordField = new JPasswordField(25);
        confirmPasswordField.setBounds(140, 140, 165, 25);
        parentPanel.add(confirmPasswordField);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 225, 165, 25);

        //handles insertion and UI refresh
        button.addActionListener(e -> {
            newUser();

            //table refresh
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllUserGUI.UserTable(panel);

        });
        parentPanel.add(button);

        passwordStatus = new JLabel("");
        passwordStatus.setBounds(10, 170, 400, 50);
        passwordStatus.setForeground(Color.red);
        parentPanel.add(passwordStatus);


    }

    //validates entered info
    //inserts data into the database using the DAO
    private void newUser() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        char[] password = passwordField.getPassword();
        char[] confirmed = confirmPasswordField.getPassword();
        boolean passwordCheck = false;
        boolean nameCheck = false;

        //validates password using a method
        if (User.validatePassword(new String(password))) {

            //checks if password matches confirm password
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

        //name cannot be empty or blank
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

        //if all checks are passed
        if (nameCheck && passwordCheck){

            String[] options = {"Admin", "Clerk"};

            //role select
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
                confirmPasswordField.setText("");
                passwordField.setText("");
                firstNameText.setText("");
                lastNameText.setText("");


            } else if (choice == 1) {
                user = new Clerk(firstName, lastName, new String(password));
                udao.addUser(user);
                JOptionPane.showMessageDialog(this, "New Clerk Created Successfully ! ");
                confirmPasswordField.setText("");
                passwordField.setText("");
                firstNameText.setText("");
                lastNameText.setText("");

            }

            //wipe passwords
            Arrays.fill(password, '\0'); //password erasure
            Arrays.fill(confirmed, '\0');
        }



    }
}

