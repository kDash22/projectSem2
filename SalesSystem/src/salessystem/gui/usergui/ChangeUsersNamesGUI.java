package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;

public class ChangeUsersNamesGUI extends JDialog {

    private final JTextField firstNameText;
    private final JTextField lastNameText;
    private final JTextField usernameText;
    private final JLabel nameStatus;
    private final JLabel UserNameStatus;

    public ChangeUsersNamesGUI(JFrame parent){
        super(parent, "Add New User", true);

        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel username = new JLabel("Username ");
        username.setBounds(10, 20, 80, 30);
        panel.add(username);

        usernameText = new JTextField(25);
        usernameText.setBounds(100, 20, 165, 25);
        panel.add(usernameText);

        UserNameStatus = new JLabel("");
        UserNameStatus.setBounds(10, 50, 500, 30);
        UserNameStatus.setForeground(Color.red);
        panel.add(UserNameStatus);

        JLabel firstName = new JLabel("First Name ");
        firstName.setBounds(10, 80, 80, 30);
        panel.add(firstName);

        firstNameText = new JTextField(25);
        firstNameText.setBounds(100, 80, 165, 25);
        panel.add(firstNameText);

        JLabel lastName = new JLabel("Last Name ");
        lastName.setBounds(10, 110, 80, 30);
        panel.add(lastName);

        lastNameText = new JTextField(25);
        lastNameText.setBounds(100, 110, 165, 25);
        panel.add(lastNameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 140, 500, 30);
        nameStatus.setForeground(Color.red);
        panel.add(nameStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 175, 165, 25);
        button.addActionListener(e -> changeUsersNames());
        panel.add(button);
    }

    public void changeUsersNames(){
            String username = usernameText.getText();
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();

            boolean nameCheck = false;
            boolean usernameCheck = false;

        if (username != null && !username.isBlank()) {

            username = username.trim();
            UserNameStatus.setText("");
            usernameCheck = true;
        } else {
            UserNameStatus.setText(" Username cannot be blank ! ");
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

        if (usernameCheck && nameCheck){
            UserDAO udao = new UserDAO();
            User user = udao.getUserByUsername(username);


            if (user != null){
                user.setFirstName(firstName);
                user.setLastName(lastName);

                udao.updateName(user.getUserID(), user);
                JOptionPane.showMessageDialog(this, " Name changed Successfully ! ");
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, " User not found ");
                usernameText.setText("");
                firstNameText.setText("");
                lastNameText.setText("");
            }
        }





    }

}
