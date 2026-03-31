package salessystem.gui.usergui;

import salessystem.dao.UserDAO;
import salessystem.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GetAllUserGUI extends JDialog {

    public GetAllUserGUI(JFrame parent){
        super(parent, "All Users", true);


        setSize(600, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        UserTable(panel);
    }
    public static void UserTable(JPanel panel){
        String[] columns = {" User ID ", " First Name ", " Last Name ", " Username ", " Role " };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,550,250);
        panel.add(scrollPane);

        UserDAO udao = new UserDAO();
        List<User> users = udao.getAllUsers();

        model.setRowCount(0);

        for (User user : users){
            Object[] row = {
                    user.getUserID(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getRole()
            };
            model.addRow(row);
        }

    }
}
