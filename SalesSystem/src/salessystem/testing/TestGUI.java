package salessystem.testing;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import salessystem.model.Admin;
import salessystem.model.UnitType;
import salessystem.model.User;


public class TestGUI  {
    
    public static List<User> users = new ArrayList<>();
    
    public TestGUI(){
        JLabel label1= new JLabel("Create new Admin");
        JLabel label2= new JLabel("Nuh uh button");


        JButton button2 = new JButton("NUH UH");
        button2.addActionListener(e-> nuhUh());

        JButton button1 = new JButton("new Admin"); //button
        button1.addActionListener(e-> createNewAdmin());

        JPanel panel = new JPanel(); //layout
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout());
        panel.add(label1);
        panel.add(button1);
        panel.add(label2);
        panel.add(button2);


        
        

        JFrame frame = new JFrame(); //window
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("TestGUI");
        frame.pack();
        frame.setVisible(true);




    }
    public void createNewAdmin(){
        Admin a1 = new Admin(1,"Kalana", "Dasanayaka", "Maekar2295!@#");
        users.add(a1);
        System.out.println(a1);
        new TestLogin();
    }
    public void nuhUh(){
        System.out.println("NUH UH!");
    }
    
    public static void main(String[] args) {
        new TestGUI();
        System.out.println(UnitType.PIECE.name());
    }

}
