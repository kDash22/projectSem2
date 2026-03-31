package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;

//provides the JDialog to change the name of user
public class ChangeUsersNamesGUI extends JDialog {

    private final JTextField firstNameText;//input field for first name
    private final JTextField lastNameText;//input field for last name
    private final JTextField userIDText;//input field for user id
    private final JLabel nameStatus;//displays errors regarding name
    private final JLabel UserIDStatus;//displays errors regarding the user id

    //initialise JDialog
    //includes
    // - user info form (left)
    // - user table (right)
    // - confirm button
    public ChangeUsersNamesGUI(JFrame parent){
        super(parent, "Change User's name", true);

        setSize(800, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //table panel
        JPanel panel = new JPanel();
        panel.setBounds(260, 20, 550,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllUserGUI.UserTable(panel);


        JLabel userId = new JLabel("User ID ");
        userId.setBounds(10, 20, 80, 30);
        parentPanel.add(userId);

        userIDText = new JTextField(25);
        userIDText.setBounds(100, 20, 165, 25);
        parentPanel.add(userIDText);

        UserIDStatus = new JLabel("");
        UserIDStatus.setBounds(10, 50, 500, 30);
        UserIDStatus.setForeground(Color.red);
        parentPanel.add(UserIDStatus);

        JLabel firstName = new JLabel("First Name ");
        firstName.setBounds(10, 80, 80, 30);
        parentPanel.add(firstName);

        firstNameText = new JTextField(25);
        firstNameText.setBounds(100, 80, 165, 25);
        parentPanel.add(firstNameText);

        JLabel lastName = new JLabel("Last Name ");
        lastName.setBounds(10, 110, 80, 30);
        parentPanel.add(lastName);

        lastNameText = new JTextField(25);
        lastNameText.setBounds(100, 110, 165, 25);
        parentPanel.add(lastNameText);

        nameStatus = new JLabel("");
        nameStatus.setBounds(10, 140, 500, 30);
        nameStatus.setForeground(Color.red);
        parentPanel.add(nameStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 175, 165, 25);

        //handles updating and UI refresh
        button.addActionListener(e -> {
            changeUsersNames();

            //table refresh
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            GetAllUserGUI.UserTable(panel);
        });
        parentPanel.add(button);
    }

    //validates the entered info
    //updates the database using the DAO
    public void changeUsersNames(){
        String userIDString = userIDText.getText();
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();

        boolean nameCheck = false;
        boolean userIDcheck = false;
        int userID = -1;

        //user id cannot be empty or blank
        if (userIDString != null && !userIDString.isBlank()) {

            userID = Integer.parseInt(userIDString);
            UserIDStatus.setText("");
            userIDcheck = true;
        } else {
            UserIDStatus.setText(" User ID cannot be blank ! ");
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

        //if all checks pass
        if (userIDcheck && nameCheck){
            UserDAO udao = new UserDAO();
            User user = udao.getUserByUserID(userID);

            if (user != null){
                user.setFirstName(firstName);
                user.setLastName(lastName);

                udao.updateName(user.getUserID(), user);
                JOptionPane.showMessageDialog(this, " Name changed Successfully ! ");

            }else {
                JOptionPane.showMessageDialog(this, " User not found ");
                userIDText.setText("");
                firstNameText.setText("");
                lastNameText.setText("");
            }
        }

    }

}
