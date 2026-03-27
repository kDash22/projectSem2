package salessystem.testing;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import salessystem.model.User;

public class TestLogin {
    
    private static JTextField userText;
    private static JPasswordField passwordText;
    
    private static JLabel loginMsg;
    
    public TestLogin(){   
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("USER");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton login = new JButton();
        login.setBounds(10,80,80,25);
        login.setText("login");
        panel.add(login);
        
        login.addActionListener(e-> verifyLogin(TestGUI.users));

        loginMsg = new JLabel("");
        loginMsg.setBounds(10,110,300,25);
        panel.add(loginMsg);

        JFrame frame = new JFrame();
        frame.setTitle("Login here.");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

}    


    
public void verifyLogin(List<User> users ){
        String username = userText.getText();
        char[] password = passwordText.getPassword();
        boolean success = false;

        for (User user : users) {
            if (username.equals(user.getUserName()) && Arrays.equals(user.getPassword().toCharArray(), password)) {
                Arrays.fill(password, ' '); // erase sensitive data
                success = true;
            }
            
        }
        if (success) {
            Arrays.fill(password, ' ');
            loginMsg.setText("Login successful");
        }
        else{
            Arrays.fill(password, ' ');
            loginMsg.setText("Login unsuccessful");
        }
    }
}   

