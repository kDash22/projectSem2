package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for deleting a user
public class DeleteUserGUI extends JDialog {

    private final JTextField textfield;//input field for username
    private final JLabel UserNameStatus;//display errors

    //initalise the JDialog
    //includes
    // - username input field
    // - confirm button
    public DeleteUserGUI(JFrame parent){
        super(parent, "Delete User",true);

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

        UserNameStatus = new JLabel("");
        UserNameStatus.setBounds(10, 50, 200, 30);
        UserNameStatus.setForeground(Color.red);
        panel.add(UserNameStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 195, 165, 25);

        //handles deletion
        button.addActionListener(e -> deleteUser());
        panel.add(button);
    }

    //validates the given info
    //deletes user from the db using the DAO
    public void deleteUser(){
        String username = textfield.getText();

        //username cannot be empty or blank
        if (username != null && !username.isBlank()) {

            username = username.trim();
            UserNameStatus.setText("");

            UserDAO udao = new UserDAO();
            User user = udao.getUserByUsername(username);

            //if user exists
            if (user != null){

                //reconfirm
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    udao.deleteUser(user.getUserID());
                    JOptionPane.showMessageDialog(this, " User deleted successfully.");
                    dispose();
                }
                else if (result == JOptionPane.NO_OPTION) {
                }
            }else {
                JOptionPane.showMessageDialog(this, " User not found ! ");
                textfield.setText("");
            }
        } else {
            UserNameStatus.setText(" Username cannot be blank ! ");
        }

    }
}
