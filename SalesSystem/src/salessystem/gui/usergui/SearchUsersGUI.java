package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import java.awt.*;

public class SearchUsersGUI extends JDialog {
    private JLabel usernameLabel = new JLabel("");
    private JLabel msg = new JLabel("");
    private JLabel name = new JLabel("");
    private JLabel userID = new JLabel("");
    private JTextField textfield;
    private JPasswordField passwordField;
    private JLabel status;
    private int choice;

    public SearchUsersGUI(JFrame parent){
        super(parent, "Search Users",true);

        String[] options = { "By User ID", "By Username"};

        choice = JOptionPane.showOptionDialog(
                //checks role
                null,
                "Select Search method",
                "Search method Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );


        setSize(600, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel userLabel = new JLabel();
        userLabel.setBounds(10,20,80,30);
        
        if (choice == 1){
            userLabel.setText("Username");
        }
        if (choice == 0){
            userLabel.setText("User ID");
        }
        
        panel.add(userLabel);

        textfield = new JTextField(25);
        textfield.setBounds(100,20,165,25);
        panel.add(textfield);

        JButton button = new JButton("Search");
        button.setBounds(10,80,80,25);
        button.addActionListener(e-> searchUser(panel));
        panel.add(button);


    }
    public int getChoice() {
        return choice;
    }

    public void searchUser(JPanel panel){
        
        String field;
        User user = null;
        UserDAO udao = new UserDAO();
        
        if (choice == 1 ){
            field = textfield.getText();
            user = udao.getUserByUsername(field);
        }
        if (choice == 0 ){
            field = textfield.getText();
            user = udao.getUserByUserID(Integer.parseInt(field));
        }
        
        if (user!=null){

            msg.setText("");
            userID.setText("");
            name.setText("");
            usernameLabel.setText("");

            msg.setText("User Found !");
            msg.setForeground(Color.black);
            msg.setBounds(10,120,400,25);

            userID.setText("User ID: "+user.getUserID());
            userID.setBounds(10,150,400,25);

            name.setText("Name : "+user.getFirstName()+" "+user.getLastName());
            name.setBounds(10,180,400,25);

            usernameLabel.setText("Username : "+user.getUserName());
            usernameLabel.setBounds(10,210,400,25);

            panel.add(msg);
            panel.add(userID);
            panel.add(name);
            panel.add(usernameLabel);
        }
        else {
            msg.setText("");
            userID.setText("");
            name.setText("");
            usernameLabel.setText("");

            msg.setText("User Not found !");
            msg.setBounds(10,120,400,25);
            msg.setForeground(Color.red);
            panel.add(msg);
        }
        panel.revalidate();
        panel.repaint();
    }


}
